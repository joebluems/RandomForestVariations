# ML Variations on MapR - Random Forest (RF)

<img align="left"  src="./images/growing.gif" width="250">

*If a decision tree fails in a random forest and no one is monitoring it, does it impact performance?*  The data scientist today has an embarrassment of riches for machine-learning libraries. It would be a classic buyer's market if most of these tools weren't already open-sourced. Selecting the right tool to get good performance used to be the key decision a data scientist would struggle with - we're seeing a seismic shift to focusing on how it gets deployed. After all, that is the **only** way in which the value of a machine-learning model is realized. <br/>
<br/>
If you have a data platform on which you can wield many tools and the flexibility to deploy your models the way you want to, the actual choice of tool can almost be incidental. To show that concept in action, we've loaded many libraries and tools onto **the MapR data platform** to illustrate how the same model training can be done in a variety of ways, depending on things like size, speed and user preference.
<br clear="left"/>

## Why Random Forest?
<img align="right"  src="./images/forest.gif" width="250">
There are many categories of machine-learning problems and the goal of the solution you're trying to build will determine which algorithms are appropriate. Classification is part of the O.G. family of ML problems  (along with estimation / forecasting and clustering) but there are many others. If you're an experienced data scientist, you've probably built a few binary classification models in your day. 
<br/>
The **Random Forest** algorithm takes a very simple component (i.e. the binary decision tree) and extends it very powerfully by building many trees (ahem, a forest...) and constantly sampling among the observations and features. This allows the data scientist to explore non-linear patterns in the data but also provides the robustness of linear estimators. It's unique in that the more you train, *the less chance that over-fitting has occurred*. You should use lots of methods to run lots of experiements to find the model (or the ensemble) with the best performance.

<br clear="right"/>
An example of one forest tree is here: 
<img src="./images/sampleTree.png" width="600">


## The Machine-Learning Workflow with RF

<img align="left"  src="./images/tree.gif" width="200">
Text goes here

<br clear="left"/>




## Vary the tools but not the Environment

<img align="left"  src="./images/randomForest.png" width="200">
Text goes here
<img align="right"  src="./images/mapr.png" width="200">
<br clear="right"/>
<br clear="left"/>

