
##############################
###### 1. DATA PREP ##########
##############################
library(sparklyr)
library(dplyr)
library(tidyr)
library(purrr)

### Note: you may need to set your SPARK_HOME environment variable for R to find spark
### Note: you will need to specify file location
sc <- spark_connect(master = "local[*]")
data_tbl <- spark_read_csv(sc, name = "data", path = "../sample10k.csv", header = TRUE, infer_schema = TRUE, delimiter = ",")
print("Importing data...")
head(data_tbl)

## create partitions table references
partition <- data_tbl %>% 
  mutate(target = as.numeric(target)) %>%
  sdf_partition(train = 0.6, test = 0.4, seed = 542)
train_tbl <- partition$train
test_tbl <- partition$test
print("Dimensions of training and testing splits:")
dim(train_tbl)
dim(test_tbl)

############################################
###### 2. TRAIN, INTERPRET MODELS ##########
############################################
ml_formula <- formula(target ~ f1 + f2 + f3 + f4 + f5 + f6 + f7 + f8 + f9 + f10 + f11 + f12)
ml_rf <- train_tbl %>% ml_random_forest(ml_formula, num_trees = 500, max_depth =5, type="classification")
print("Model Trained:")
print(ml_rf)
summary(ml_rf)

### feature importance for random forest
feature_importance <- tibble()
feature_importance <- ml_tree_feature_importance(ml_rf) %>%
  mutate(Model = "Random Forest") %>%
  rbind(feature_importance, .)
feature_importance


##########################################
####### 3. EVALUATE MODEL (AUC) ##########
##########################################
pred <- ml_predict(ml_rf, test_tbl) 
print("AUC for test data set:")
ml_binary_classification_evaluator(pred,metric_name = "areaUnderROC")

