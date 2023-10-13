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
  <a href="./CALLING_GITHUB_JAVA_DEPENDENCY.txt">Calling Dependency</a> |
  <a href="#dependencyInjection">Dependency Injection</a> |
  <a href="#responses">Responses</a> |
  <a href="#usage-single-data">Usage</a> |
  <a href="#privacy">Privacy</a> |
  <a href="#clone">Clone </a> 
</p>


## Features
<a id="features"></a>
```bash
 - This is a java (SpringBoot) library to be used for java inter-service communication
 - It uses RestTemplate
 - It is generic in nature. Accepts any type of request body or parameter and response
```

## Prerequisites
<a id="prerequisites"></a>
```bash
- Java 17 or higher
```

## Dependency Injection
<a id="dependencyInjection"></a>
```bash
  @Autowired
    protected RemoteAssistance remoteAssistance;
```

## Response Involved
<a id="responses"></a>
- <a href="#usage-single-data">Normal Data Response</a>
- <a href="#usage-list-data">Data List Response</a>

## Usage  
Steps: 
### A. Import libray as a jar or by dependency
### B. Normal Data Response
<a id="usage-single-data"></a>
###### 01. FOR GETTING SINGLE DATA RESPONSE (GET METHOD AS A DEFAULT HTTP METHOD TYPE):
```bash
  ProfileDto respProfileData = remoteAssistance.setHeaders(request).data("http://localhost:6666/app/data", ProfileDto.class);
 
  NOTE: request is HttpServletRequest
```

###### 02. FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE):
```bash
  ProfileDto respProfileData = remoteAssistance.setHeaders(request).data("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.GET);
 
  NOTE: request is HttpServletRequest
```

###### 03. FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST BODY AS OBJECT OF ANY TYPE:
```bash
  ProfileDto respProfileData= remoteAssistance.setHeaders(request).data("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, new Office("HQ"));
 
  NOTE: request is HttpServletRequest
```

###### 04. FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST BODY AS OBJECT LIST:
```bash
  ProfileDto respProfileData = remoteAssistance.setHeaders(request).data("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, List.of("HQ","REGIONAL"));
 
  NOTE: request is HttpServletRequest
```

###### 05. FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST PARAMETERS:
```bash
  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("param1", "value1");
        params.add("param2", "value2");
        
  ProfileDto respProfileData = remoteAssistance.setHeaders(request).data("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, params);
 
  NOTE: request is HttpServletRequest
```

###### 06. FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST PARAMETERS + REQUEST BODY:
```bash
  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("param1", "value1");
        params.add("param2", "value2");
        
  ProfileDto respProfileData = remoteAssistance.setHeaders(request).data("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, params, new Office("HQ"));
 
  NOTE: request is HttpServletRequest
```

###### 07. FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST PARAMETERS + REQUEST BODY AS LIST:
```bash
  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("param1", "value1");
        params.add("param2", "value2");
        
  ProfileDto respProfileData = remoteAssistance.setHeaders(request).data("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, params, List.of("HQ","REGIONAL"));
 
  NOTE: request is HttpServletRequest
```
### C. Data List Response
<a id="usage-list-data"></a>
###### 01. FOR GETTING LIST DATA RESPONSE (BY USING GET AS A DEFAULT HTTP METHOD TYPE):
```bash
  List<ProfileDto> respProfileData = remoteAssistance.setHeaders(request).list("http://localhost:6666/app/data", ProfileDto.class);
 
  NOTE: request is HttpServletRequest
```

###### 02. FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) :
```bash
  List<ProfileDto> respProfileDataList = remoteAssistance.setHeaders(request).list("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.GET);
 
  NOTE: request is HttpServletRequest
```

###### 03. FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + PARAMETERS + REQUEST BODY AS OBJECT:
```bash
  List<ProfileDto> respProfileDataList = remoteAssistance.setHeaders(request).list("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, new Office("HQ"));
 
  NOTE: request is HttpServletRequest
```

###### 04. FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST BODY AS OBJECT LIST:
```bash
  List<ProfileDto> respProfileDataList = remoteAssistance.setHeaders(request).list("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, List.of("HQ","REGIONAL"));
 
  NOTE: request is HttpServletRequest
```

###### 05. FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + PARAMETERS:
```bash
  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("param1", "value1");
        params.add("param2", "value2");
        
  List<ProfileDto> respProfileDataList = remoteAssistance.setHeaders(request).list("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, params);
 
  NOTE: request is HttpServletRequest
```

###### 06. FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + PARAMETERS + REQUEST BODY AS OBJECT:
```bash
  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("param1", "value1");
        params.add("param2", "value2");
        
  List<ProfileDto> respProfileDataList = remoteAssistance.setHeaders(request).list("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, params, new Office("HQ"));
 
  NOTE: request is HttpServletRequest
```

###### 07. FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + PARAMETERS + REQUEST BODY AS OBJECT LIST:
```bash
  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("param1", "value1");
        params.add("param2", "value2");
        
  ProfileDto respProfileDataList = remoteAssistance.setHeaders(request).list("http://localhost:6666/app/data", ProfileDto.class, HttpMethod.POST, params, List.of("HQ","REGIONAL"));
 
  NOTE: request is HttpServletRequest
```


## Privacy
<a id="privacy"></a>
```bash
This is a private library. It is available only by using pccb intranet.
```

## Clone the repository
<a id="clone"></a>
```bash
git clone https://github.com/jamalakida/remote-assitance
```


