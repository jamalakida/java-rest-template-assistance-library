<h1 align="center">
  <br>
  <img src="jamal.jpg" alt="Jamal Logo" width="200">
  <br>
  JAVA INTER-SERVICE COMMUNICATION LIBRARY.
  <br>
</h1>

<p align="center">
  A Spring boot library created at 2023 for the purpose of facilitating rest template inter-service communication.
</p>

<p align="center">
  <a href="#features">Features</a> |
  <a href="#prerequisites">Prerequisites</a> |
  <a href="https://drive.google.com/file/d/1DJoYxvv-LwuQ1I6JH-gu9MfWpW77gh1A/view?usp=drive_link">Dependency Access Token</a> |
  <a href="#mavenCentralDependency">Maven Central/Repository Dependency</a> |
  <a href="#libraryImportation">Library Importation</a> |
  <a href="#dependencyInjection">Dependency Injection</a> |
  <a href="#responses">Responses</a> |
  <a href="#usage">Usage</a> |
  <a href="#privacy">Privacy</a> |
  <a href="#clone">Clone </a> 
</p>


## <a id="features"></a> Features
```bash
 - This is a java (SpringBoot) library to be used for java inter-service communication
 - It uses RestTemplate
 - It is generic in nature. Accepts any type of request body or parameter and response
 - It works with any java version strating with java 8
 - It can be used by both Maven and Gradle 
 - It can be used in Java Core and Any Java Framework (Spring, SpringBoot, Kotlin)
 - It has multiple importation methods as it can be imported by dependency or by .jar file
 - It handles any type of response (Single data & data list response)
```

## <a id="prerequisites"></a> Prerequisites
```bash
- Any Java version (>= java 8)
```
## <a id="libraryImportation"></a>Library Importation
###### 1. Importing by dependency <a id="importByDependency"></a>
- A. <a href="./importation_methods/CALLING_GITHUB_JAVA_DEPENDENCY.txt"> GitHub</a>
- B. <a href="https://central.sonatype.com/artifact/io.github.jamalakida/java-rest-template-library">Maven Central</a>
- C. <a href="https://mvnrepository.com/artifact/io.github.jamalakida/java-rest-template-library/1.0.0">Maven Repository</a>

###### 2. Importing by jar file <a id="importByJarFile"></a>
- <a href="https://github.com/jamalakida/java-rest-template-assistance-library/releases/download/prod/rest-template-library-1.0.0.jar">Download release package</a> | <a href="./importation_methods/IMPORT_AS_JAR_FILE.txt"> Instructions</a>

<a id="mavenCentralDependency"></a>
### <a id="mavenCentralDependency"></a>Maven Central Or Maven Repository Dependency
- For Gradle Project
```bash
  implementation 'io.github.jamalakida:java-rest-template-library:1.0.0'
```

-  For Maven Project
```bash
<dependency>
  <groupId>io.github.jamalakida</groupId>
  <artifactId>java-rest-template-library</artifactId>
  <version>1.0.0</version>
</dependency>
```

- For Kotlin, SBT, Ivy, Grape, Leiningen, Buildr ->
  <a href="https://central.sonatype.com/artifact/io.github.jamalakida/java-rest-template-library">Maven Central</a> | <a href="https://mvnrepository.com/artifact/io.github.jamalakida/java-rest-template-library/1.0.0">Maven Repository</a> 

## <a id="responses"></a> Response Involved
- <a href="#usage-single-data">Normal Data Response</a>
- <a href="#usage-list-data">Data List Response</a>

## <a id="usage"></a> Usage  
Steps: 
### A. Import libray as:
- <a href="#importByDependency">Dependency</a>
- <a href="#importByJarFile">Jar File</a>

### B. Instantiate the bean of the imported Remote Assistance Library (Use one the following technique)
######  Alternative 1: If you have main class in your project
- By Annotation: EnableRemoteAssistance
```bash
@EnableRemoteAssistance
```

- OR: Import RemoteAssistance class
```bash
@Import({RemoteAssistance.class})
```
- OR: Scan remote assistance package as follows
```bash
@ComponentScan(basePackages = {"rest.template.library", "your_project"})

NOTE: 
    - your_project is the scan for your working project Example: tz.go.pccb.hrms.app
    - You can use Alternative 2 instead on 1 even if you have main class in your project
```

###### Alternative 2: If you don't have main class in your project
-  Create LibraryConfig configuration class 
- Add the following codes
```bash
@Configuration
public class LibraryConfig {
    @Bean
    public RemoteAssistance remoteAssistance() {
        return new RemoteAssistance(); // Instantiating the bean here.
    }
}
```

### <a id="dependencyInjection"></a> C. Dependency Injection (In a class, Abstract or Interface)
```bash
  @Autowired
    protected RemoteAssistance remoteAssistance;
    
```


### D. <a id="usage-single-data"></a> Normal Data Response
###### 01. FOR GETTING SINGLE DATA RESPONSE (GET METHOD AS A DEFAULT HTTP METHOD TYPE):
```bash
  Example: 
  
  ProfileDto respProfileData = remoteAssistance.setHeaders(request).data("http://localhost:6666/app/data", ProfileDto.class);
 
  NOTE: request is HttpServletRequest
```

###### 02. FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE):
```bash
  Example: 
  
  ProfileDto respProfileData = remoteAssistance.setHeaders(request).data("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.GET);
 
  NOTE: request is HttpServletRequest
```

###### 03. FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST BODY AS OBJECT OF ANY TYPE:
```bash
  Example: 
  
  ProfileDto respProfileData= remoteAssistance.setHeaders(request).data("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, new Office("HQ"));
 
  NOTE: request is HttpServletRequest
```

###### 04. FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST BODY AS OBJECT LIST:
```bash
  Example: 
  
  ProfileDto respProfileData = remoteAssistance.setHeaders(request).data("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, List.of("HQ","REGIONAL"));
 
  NOTE: request is HttpServletRequest
```

###### 05. FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST PARAMETERS:
```bash
  Example: 
  
  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("param1", "value1");
        params.add("param2", "value2");
        
  ProfileDto respProfileData = remoteAssistance.setHeaders(request).data("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, params);
 
  NOTE: request is HttpServletRequest
```

###### 06. FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST PARAMETERS + REQUEST BODY:
```bash
  Example: 
  
  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("param1", "value1");
        params.add("param2", "value2");
        
  ProfileDto respProfileData = remoteAssistance.setHeaders(request).data("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, params, new Office("HQ"));
 
  NOTE: request is HttpServletRequest
```

###### 07. FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST PARAMETERS + REQUEST BODY AS LIST:
```bash
  Example: 
  
  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("param1", "value1");
        params.add("param2", "value2");
        
  ProfileDto respProfileData = remoteAssistance.setHeaders(request).data("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, params, List.of("HQ","REGIONAL"));
 
  NOTE: request is HttpServletRequest
```
### <a id="usage-list-data"></a> E. Data List Response
###### 01. FOR GETTING LIST DATA RESPONSE (BY USING GET AS A DEFAULT HTTP METHOD TYPE):
```bash
  Example: 
  
  List<ProfileDto> respProfileData = remoteAssistance.setHeaders(request).list("http://localhost:6666/app/data", ProfileDto.class);
 
  NOTE: request is HttpServletRequest
```

###### 02. FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) :
```bash
  Example: 
  
  List<ProfileDto> respProfileDataList = remoteAssistance.setHeaders(request).list("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.GET);
 
  NOTE: request is HttpServletRequest
```

###### 03. FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + PARAMETERS + REQUEST BODY AS OBJECT:
```bash
  Example: 
  
  List<ProfileDto> respProfileDataList = remoteAssistance.setHeaders(request).list("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, new Office("HQ"));
 
  NOTE: request is HttpServletRequest
```

###### 04. FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST BODY AS OBJECT LIST:
```bash
  Example: 
  
  List<ProfileDto> respProfileDataList = remoteAssistance.setHeaders(request).list("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, List.of("HQ","REGIONAL"));
 
  NOTE: request is HttpServletRequest
```

###### 05. FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + PARAMETERS:
```bash
  Example: 
  
  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("param1", "value1");
        params.add("param2", "value2");
        
  List<ProfileDto> respProfileDataList = remoteAssistance.setHeaders(request).list("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, params);
 
  NOTE: request is HttpServletRequest
```

###### 06. FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + PARAMETERS + REQUEST BODY AS OBJECT:
```bash
  Example: 
  
  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("param1", "value1");
        params.add("param2", "value2");
        
  List<ProfileDto> respProfileDataList = remoteAssistance.setHeaders(request).list("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, params, new Office("HQ"));
 
  NOTE: request is HttpServletRequest
```

###### 07. FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + PARAMETERS + REQUEST BODY AS OBJECT LIST:
```bash
  Example: 
  
  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("param1", "value1");
        params.add("param2", "value2");
        
  ProfileDto respProfileDataList = remoteAssistance.setHeaders(request).list("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, params, List.of("HQ","REGIONAL"));
 
  NOTE: request is HttpServletRequest
```


## <a id="privacy"></a> Privacy
```bash
This is a public library. It is available and can be used by any one.
```

## <a id="clone"></a> Clone the repository
```bash
git clone https://github.com/jamalakida/java-rest-template-assistance-library.git
```


