## Random Forest with sklearn
<img align="left" src="../images/scikit.png" width="150">

The python library **scikit-learn** is a dependable collection of tools that are among the easiest and most user-friendly packages there are. It's built on numpy and scipy, so if you are comfortable with python, this will be one of your first stops. For those who are newer to machine-learning, there's a good chance that you learned how to build models with this package. This is also a really good choice to estimate the degree to which your problem can be solved or quick feature assessments. 
<br clear="left"/>

### THE SCOOP
A model built with **scikit-learn is lightweight and portable**. This makes it a good candidate for being *deployed via API in a docker container*. If your scoring job is extrememly large or feature extraction is complex, this model alone won't help you, but you can achieve scaling with a tool like Kubernetes orchestrating those containers. On the training side, it's a **good choice for a quick-and-dirty model that gives you a good baseline without custom coding**. Why should you re-write the steps to calculate AUC when it's already done for you? You can always paste the code into a notebook if command-line interfaces aren't your thing. 
<br/>

### THE SET-UP
<img align="right" src="../images/pandas-logo.png" width="200">

Setup for this example is minimal. We use the pandas library which makes the data manipulation much easier and further reduces code. Below is a quick test to show if you have the right libraries. If python complains, use pip install <library>, or have your cluster admin do(or sudo) it for you. <br/>
<br/>
Isn't MapR a distributed file system? And don't you need special commands to interact with distributed file systems? It is but you don't, because MapR's file system is POSIX-compliant. You can run any program that work on Linux without modification!   
<br clear="right"/>

```
python 
Python 3.6.3 (default, Mar 20 2018, 13:50:41) 
[GCC 4.8.5 20150623 (Red Hat 4.8.5-16)] on linux
Type "help", "copyright", "credits" or "license" for more information.
>>> import sklearn, pandas, joblib
>>> 
```

### THE RUN
To run the script, simply execute it with python. It doesn't get easier than that. Selected output appears below:
```
python rf.py 

Raw Data Summary: 
             target            f1            f2  ...           f10           f11           f12
count  10000.000000  10000.000000  10000.000000  ...  10000.000000  10000.000000  10000.000000
mean       0.244200      0.009014      0.011106  ...      0.039972      0.018248     -0.042597

Modeling Populations: 
Train X & Y Shape ::  (6000, 12) (6000,)
Test X & Y Shape ::  (4000, 12) (4000,)

Trained model :: 
RandomForestClassifier(bootstrap=True, class_weight=None, criterion='gini',
            max_depth=5, max_features='auto', max_leaf_nodes=None,
            min_impurity_decrease=0.0, min_impurity_split=None,
            min_samples_leaf=1, min_samples_split=2,
            min_weight_fraction_leaf=0.0, n_estimators=500, n_jobs=None,
            oob_score=False, random_state=None, verbose=0,
            warm_start=False)

Feature Importances: 
f1 0.08698154450445926
...

AUC for Testing Data: 
0.5
```
<br/>

Don't expect much performance if you use the synthetic data. An AUC of 0.5 = ML equivalent of a coin flip. Additionally, there is a file created called **rfmodel.joblib** that is written using joblib. This model can be used for deployment.

### THE WRAP-UP

There are three basic flavors of deploying the model you just wrote out to the MapR file system:
1. For small to medium batches, simply **run it as a python script, possibly scheduled with crontab**.
1. For real-time scenarios, download the **mapr_streams_python library, stream your features through a Producer, and then add the model in your Consumer**
1. For meeting serious SLA's and heavy workloads, put the model into a **lightweight container, serve it with an API such as Flask, then orchestrate with Kubernetes** to meet demands of the workload.

<p align="middle">
  <img src="../images/docker.png" width="150" /> 
  <img src="../images/flask-logo.png" width="150" />
  <img src="../images/kubernetes.png" width="150" />
</p>

