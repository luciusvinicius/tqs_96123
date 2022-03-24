# TQS - Lab 3

## Lab3_1

**a) Identify a couple of examples on the use of AssertJ expressive methods chaining.**

​		

**b) Identify an example in which you mock the behavior of the repository (and avoid involving a database). **

​		No exemplo D é utilizado um mock para substituir a base de dados providenciada pelo repositório. Por exemplo, na linha 54 onde é feito `mvc.perform(...)`, esse mvc está a substituir a base de dados.



**c) What is the difference between standard @Mock and @MockBean?**

​		O Mock é uma classe do Maven em geral, enquanto o MockBean é uma classe provinda do Spring Boot (SB) e é utilizada junto com o application context do próprio SB. Logo, se o Mock precisar utilzar de alguma dependência do SB, usa-se o MockBean, caso contrário o Mock.