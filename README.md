# FreeAgentApplication
This application shows the current currency rates in the market and allows the user to get the last five days history of the selected currency 

Used ApiLayer platform to get the currency date by creating a free subscription. App shows error message when monthly/daily access limit is reached.
Clean architecture used to build the app with Hilt as dependency injection framework. Unit testing is done using JUnit and Mockito. 

Introduced Compose for currency history screen where the data is listed using LazyColumn. 

Introduced Github actions to run the test cases. Webhook added.
