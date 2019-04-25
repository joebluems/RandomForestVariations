package com.mapr.randomforest 

import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.DataFrame
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.classification.{RandomForestClassifier, RandomForestClassificationModel}
import org.apache.spark.ml.evaluation.ClusteringEvaluator
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
import org.apache.spark.ml.tree.{Node, InternalNode, LeafNode, Split,CategoricalSplit, ContinuousSplit}
import scala.collection.mutable.Builder
import org.apache.spark.ml.util
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Logger
import org.apache.log4j.Level

object Main extends App { 
  Logger.getLogger("org").setLevel(Level.WARN)
  var sampleFile = "../sample10k.csv"
    
  if (args.length == 1) {
      sampleFile = args(0)
  }

  val spark = SparkSession.builder.master("local").appName("rforest").getOrCreate()
  import spark.implicits._

  //load data , create pipeline , generate train/test
  val df = spark.read.option("header", "true").
     option("inferSchema", "true").csv(sampleFile)
  println("\nSample of Input Data...")
  df.show()

  /// random forest pipeline
  def getRandomPipe( continuousFeatures: Seq[String] ): Pipeline = {
   val assembler = new VectorAssembler().setInputCols((continuousFeatures ).toArray).
     setOutputCol("features")
   val rf = new RandomForestClassifier().
     setLabelCol("target").setFeaturesCol("features").
     setNumTrees(500).setMaxDepth(4).setSeed(199)
   val pipeline = new Pipeline().setStages(Array(assembler,rf))
   pipeline
  }

  /// sample and create pipelines
  val continuousFeatures = Seq("f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "f10", "f11", "f12")
  val Array(train, test) = df.randomSplit(Array(0.6, 0.4), seed=199)
  val pipe_rf = getRandomPipe( continuousFeatures)

  // fit rf model on train, display feature importance 
  val model_rf = pipe_rf.fit(train)
  val importances = model_rf.stages.last.asInstanceOf[RandomForestClassificationModel].featureImportances
  println("\nFeature Importance for Random Forest...")
  for (i <- 0 until importances.size) {
      println(continuousFeatures(i),importances.apply(i))
  }

  //evaluate on test set
  val evaluatorAUROC = new BinaryClassificationEvaluator().setLabelCol("target").setMetricName("areaUnderROC").setRawPredictionCol("probability")
  val pred_rf = model_rf.transform(test)
  val auc_rf = evaluatorAUROC.evaluate(pred_rf)
  println("\nAUC for Random Forest (test set) ...")
  println(auc_rf)

}
