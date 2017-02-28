Pine: Machine Learning Prediction As A Service
==============================================

When we integrate a (typical, non-machine learning) software system with a machine learning system (for example, a prediction model), it is often very complex. For example, it is very likely that the machine learning system is not scalable, and has to be re-implemented. It is also very likey that the two systems are developed under very different development environment, and developed using different languages, thus the integration can be quite challenging. All these make the integration take way too long time in reality.

If we expose the machine learning system functionality as a web service, the integration can be quite easy. The software system does not need to care how the machine learning system is implemented, and it just need to send http request to the machine learning system. The machine learning implementation could be focused on scalability and prediction power, instead of integration with other systems.

This code shows you how to expose the prediction as a scalable web service. The web service architecutre makes the integration between software systems and machine learning systems an easy task.

Shaojun Zhao (shaojun@ebay.com)  
April 2016
