## Random Forest with Spark, scala application and pyspark
<img align="left" src="../images/spark.png" width="150">

<br clear="left"/>

### THE SCOOP
<img align="right" src="../images/jupyter.png" width="150">

<br clear="right"/>

### THE SET-UP
There are two options for training the random forest with Spark: pyspark with jupyter and submitting as a scala application.
#### pyspark
To run the pyspark notebook, you'll need jupyter and a few libraries. You'll also need to set your SPARK_HOME environment variable so python knows where to find it. Once that's ready, launch the notebook.
```
export SPARK_HOME=/opt/mapr/spark/spark-2.3.2/
echo $SPARK_HOME
/opt/mapr/spark/spark-2.3.2/
python 
Python 3.6.3 (default, Mar 20 2018, 13:50:41) 
[GCC 4.8.5 20150623 (Red Hat 4.8.5-16)] on linux
Type "help", "copyright", "credits" or "license" for more information.
>>> import findspark
>>> ctrl-d
jupyter notebook
```

#### scala app
For this option, we need to compile the source code. You must have sbt loaded to build the jar file. Make sure your spark and scala versions match what's in the file **build.sbt**.
```
cat build.sbt 
  scalaVersion := "2.11.8"
  val sparkVersion = "2.3.2"
sbt package
[info] Done packaging.
[success] Total time: 24 s, completed Apr 30, 2019 3:57:21 PM
```
If your code worked, it should create a jar in the local folder **./target/scala-2.11/** which we will need to run the code in the next step.

### THE RUN
#### pyspark

#### scala 
After you've build the application

Don't expect much performance if you use the synthetic data. An AUC of 0.5 = ML equivalent of a coin flip. Additionally, there is a file created called **rfmodel.joblib** that is written using joblib. This model can be used for deployment.

### THE WRAP-UP
