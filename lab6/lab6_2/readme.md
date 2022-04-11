# Lab 6.2

f) O código passou na qualidade de software. Ele possui apenas um bug acerca do Random, que se enquadra mais na condição de eficiência e a de números verdadeiramentes não aleatórios. 

Além disso, possui alguns Code Smells, porém nada que deixe o projeto em si necessariamente como mau.



| Issue              | Problem description                                          | How to solve                                                 |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Bug                | Create a new `Random` object each time a random value is needed is inefficient and may produce numbers which are not random depending on the JDK. | Change `Random` to `SecureRandom`                            |
| Vulnerability      | No vulnerabilities                                           | ----                                                         |
| Code smell (major) | The return type shoud be an interface instead than implementation itself | Return a `List` interface rather than the `ArrayList` implementation |