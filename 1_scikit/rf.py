#### IMPORTS #####
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import roc_auc_score

#### method to split the data ###
def split_dataset(dataset, train_percentage, feature_headers, target_header):
  # Split dataset into train and test dataset
  train_x, test_x, train_y, test_y = train_test_split(dataset[feature_headers], 
      dataset[target_header], train_size=train_percentage)
  return train_x, test_x, train_y, test_y

#### train the RF classifier ###
def random_forest_classifier(features, target,numTrees, depth):
  clf = RandomForestClassifier(n_estimators=numTrees, max_depth=depth)
  clf.fit(features, target)
  return clf

def main():
  # Load the csv file into pandas dataframe
  dataset = pd.read_csv("../sample10k.csv")
  print("\nRaw Data Summary: ")
  print(dataset.describe())
  features = ['f1','f2','f3','f4','f5','f6','f7','f8','f9','f10','f11','f12']
  train_x, test_x, train_y, test_y = split_dataset(dataset, 0.6, features, "target")

  # Train and Test dataset size details
  print("\nModeling Populations: ")
  print("Train X & Y Shape :: ", train_x.shape, train_y.shape)
  print("Test X & Y Shape :: ", test_x.shape, test_y.shape)

  # Train model and print feature importance
  rf = random_forest_classifier(train_x, train_y, 500, 5)
  print("\nTrained model :: ")
  print(rf)

  print("\nFeature Importances: ")
  for i in range(0,len(features)):
    print(features[i],rf.feature_importances_[i])

  # Calculate AUC on test data
  print("\nAUC for Testing Data: ")
  predictions = rf.predict(test_x)
  print(roc_auc_score(test_y, predictions))
  print("\n")

if __name__ == "__main__":
    main()
