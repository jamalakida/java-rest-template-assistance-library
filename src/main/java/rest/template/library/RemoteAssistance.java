package rest.template.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class RemoteAssistance {

    //For initializing RetTemplate as a bean
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){return new RestTemplate();}

    private MultiValueMap<String, String> headers;

    /***----------------------------------------------------------------------------------------------------------------
     * FOR SETTING HEADERS
     * @param request - HttpServletRequest
     * @return - return constructor of the class
    ------------------------------------------------------------------------------------------------------------------*/
    public RemoteAssistance setHeaders(@NonNull HttpServletRequest request){
        headers = new HttpHeaders();
        request.getHeaderNames().asIterator().forEachRemaining( name -> headers.set( name, request.getHeader(name) ) );
        return this;
    }

    //********************************************************************** FOR SINGLE DATA RESPONSE *****************************************************************************************

    /**-----------------------------------------------------------------------------------------------------------------
     * FOR GETTING SINGLE DATA RESPONSE (GET METHOD AS A DEFAULT HTTP METHOD TYPE)
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @return - return single data response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    public <T> T data(@NonNull String endPoint, @NonNull Class<T> returnedObjectClass) throws JsonProcessingException {
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        return this.returnedDataFromRemoteEndpoint(endPoint, returnedObjectClass, HttpMethod.GET, requestEntity);
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE)
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @return - return single data response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    public <T> T data(@NonNull String endPoint, @NonNull Class<T> returnedObjectClass, HttpMethod methodType) throws JsonProcessingException {
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        return this.returnedDataFromRemoteEndpoint(endPoint, returnedObjectClass, methodType, requestEntity);
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST BODY AS OBJECT OF ANY TYPE
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @param requestBodyObject - Request Body of any type
     * @return - return single data response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    public <T> T data(@NonNull String endPoint, @NonNull  Class<T> returnedObjectClass, @NonNull HttpMethod methodType, @NonNull Object requestBodyObject) throws JsonProcessingException {
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBodyObject, headers);
        return this.returnedDataFromRemoteEndpoint(endPoint, returnedObjectClass, methodType, requestEntity);
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST BODY AS OBJECT LIST
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @param requestBodyObjectList - Request Body of any type
     * @return - return single data response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    public <T> T data(@NonNull String endPoint, @NonNull  Class<T> returnedObjectClass, @NonNull HttpMethod methodType, @NonNull List<Object>  requestBodyObjectList) throws JsonProcessingException {
        HttpEntity<List<Object>> requestEntity = new HttpEntity<>(requestBodyObjectList, headers);
        return this.returnedDataFromRemoteEndpoint(endPoint, returnedObjectClass, methodType, requestEntity);
    }

    /***----------------------------------------------------------------------------------------------------------------
     * FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST PARAMETERS
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @param linkedMultiValueRequestParams - Request Params as LinkedMultiValues
     * @return  - return single data response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    public <T> T data( @NonNull String endPoint, @NonNull Class<T> returnedObjectClass, @NonNull HttpMethod methodType,
                       @RequestParam MultiValueMap<String, @NonNull String> linkedMultiValueRequestParams) throws JsonProcessingException {
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(endPoint)
                .queryParams(linkedMultiValueRequestParams);
        return this.returnedDataFromParameterizedRemoteEndpoint(endPoint, returnedObjectClass, methodType, requestEntity, uriComponentsBuilder);
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST PARAMETERS + REQUEST BODY
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @param linkedMultiValueRequestParams - Request Params as LinkedMultiValues
     * @param requestBodyObject - Request Body of any type
     * @return  - return single data response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    public <T> T data( @NonNull String endPoint, @NonNull Class<T> returnedObjectClass, @NonNull HttpMethod methodType,
                       @RequestParam MultiValueMap<String, @NonNull String> linkedMultiValueRequestParams, @NonNull Object requestBodyObject) throws JsonProcessingException {
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBodyObject, headers);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(endPoint)
                .queryParams(linkedMultiValueRequestParams);
        return this.returnedDataFromParameterizedRemoteEndpoint(endPoint, returnedObjectClass, methodType, requestEntity, uriComponentsBuilder);
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR GETTING SINGLE DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST PARAMETERS + REQUEST BODY AS LIST
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @param linkedMultiValueRequestParams - Request Params as LinkedMultiValues
     * @param requestBodyObjectList - Request Body with data list
     * @return - return single data response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    public <T> T data( @NonNull String endPoint, @NonNull Class<T> returnedObjectClass, @NonNull HttpMethod methodType,
                       @RequestParam MultiValueMap<String, @NonNull String> linkedMultiValueRequestParams, @NonNull List<Object> requestBodyObjectList) throws JsonProcessingException {
        HttpEntity<List<Object>> requestEntity = new HttpEntity<>(requestBodyObjectList, headers);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(endPoint)
                .queryParams(linkedMultiValueRequestParams);
        return this.returnedDataFromParameterizedRemoteEndpoint(endPoint, returnedObjectClass, methodType, requestEntity, uriComponentsBuilder);
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR COMMUNICATING WITH REMOTE ENDPOINT (USING UriComponentsBuilder) FOR PARAMETERIZED ENDPOINT
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @param requestEntity - Request Entity
     * @param uriComponentsBuilder - Uri Component Builder
     * @return - return single data response
     * @param <T>  - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    private <T> @Nullable T returnedDataFromParameterizedRemoteEndpoint(@NonNull String endPoint, @NonNull  Class<T> returnedObjectClass, @NonNull HttpMethod methodType, HttpEntity requestEntity, @NonNull UriComponentsBuilder uriComponentsBuilder) throws JsonProcessingException {
        RestTemplate anyTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = anyTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                methodType,
                requestEntity,
                new ParameterizedTypeReference<T>() {}
        );
        return responseEntity.getStatusCode().is2xxSuccessful() ? this.getMappedData(responseEntity.getBody(), returnedObjectClass) : null;
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR COMMUNICATING WITH REMOTE ENDPOINT
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @param requestEntity - Request Entity
     * @return return single data response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    private <T> @Nullable T returnedDataFromRemoteEndpoint(@NonNull String endPoint, @NonNull  Class<T> returnedObjectClass, @NonNull HttpMethod methodType, HttpEntity requestEntity) throws JsonProcessingException {
        RestTemplate anyTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = anyTemplate.exchange(
                endPoint,
                methodType,
                requestEntity,
                new ParameterizedTypeReference<T>() {}
        );
        return responseEntity.getStatusCode().is2xxSuccessful() ? this.getMappedData(responseEntity.getBody(), returnedObjectClass) : null;
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR CONVERTING RESPONSE DATA FROM LINKED-HASHMAP TO RESPECTIVE DATA
     * @param genericListFromResponse - Generic Response from remote endpoint
     * @param returnedObjectClass - Object class of the returned data
     * @return - return single data response
     * @param <T>  - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    private @NonNull <T> T getMappedData(T genericListFromResponse, Class<T> returnedObjectClass) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode nodeData =  mapper.readTree(mapper.writeValueAsString(genericListFromResponse));
        return mapper.treeToValue(nodeData, returnedObjectClass);
    }


    //*********************************************************** FOR DATA LIST RESPONSE **********************************************************************************

    /***----------------------------------------------------------------------------------------------------------------
     * FOR GETTING LIST DATA RESPONSE (BY USING GET AS A DEFAULT HTTP METHOD TYPE)
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @return - return data list response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    public <T> List<T> list(@NonNull String endPoint, @NonNull Class<T> returnedObjectClass) throws JsonProcessingException {
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        return this.returnedListFromRemoteEndpoint(endPoint, returnedObjectClass, HttpMethod.GET, requestEntity);
    }

    /***----------------------------------------------------------------------------------------------------------------
     * FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE)
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @return - return data list response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    public <T> List<T> list(@NonNull String endPoint, @NonNull Class<T> returnedObjectClass, @NonNull HttpMethod methodType) throws JsonProcessingException {
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        return this.returnedListFromRemoteEndpoint(endPoint, returnedObjectClass, methodType, requestEntity);
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + PARAMETERS + REQUEST BODY AS OBJECT
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @param requestBodyObject - Request Body of any type
     * @return  - return data list response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    public <T> List<T> list(@NonNull String endPoint, @NonNull  Class<T> returnedObjectClass, @NonNull HttpMethod methodType, @NonNull Object requestBodyObject) throws JsonProcessingException {
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBodyObject, headers);
        return this.returnedListFromRemoteEndpoint(endPoint, returnedObjectClass, methodType, requestEntity);
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + REQUEST BODY AS OBJECT LIST
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @param requestBodyObjectList - Request Body of data list
     * @return - return data list response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    public <T> List<T> list(@NonNull String endPoint, @NonNull  Class<T> returnedObjectClass, @NonNull HttpMethod methodType, @NonNull List<Object>  requestBodyObjectList) throws JsonProcessingException {
        HttpEntity<List<Object>> requestEntity = new HttpEntity<>(requestBodyObjectList, headers);
        return this.returnedListFromRemoteEndpoint(endPoint, returnedObjectClass, methodType, requestEntity);
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + PARAMETERS
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @param linkedMultiValueRequestParams - Request Params as LinkedMultiValues
     * @return - return data list response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    public <T> List<T> list( @NonNull String endPoint, @NonNull Class<T> returnedObjectClass, @NonNull HttpMethod methodType,
                             @RequestParam MultiValueMap<String, @NonNull String> linkedMultiValueRequestParams) throws JsonProcessingException {
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(endPoint)
                .queryParams(linkedMultiValueRequestParams);
        return this.returnedListFromParameterizedRemoteEndpoint(endPoint, returnedObjectClass, methodType, requestEntity, uriComponentsBuilder);
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + PARAMETERS + REQUEST BODY AS OBJECT
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @param linkedMultiValueRequestParams - Request Params as LinkedMultiValues
     * @param requestBodyObject - Request Body of any type
     * @return - return data list response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    public <T> List<T> list( @NonNull String endPoint, @NonNull Class<T> returnedObjectClass, @NonNull HttpMethod methodType,
                             @RequestParam MultiValueMap<String, @NonNull String> linkedMultiValueRequestParams, @NonNull Object requestBodyObject) throws JsonProcessingException {
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBodyObject, headers);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(endPoint)
                .queryParams(linkedMultiValueRequestParams);
        return this.returnedListFromParameterizedRemoteEndpoint(endPoint, returnedObjectClass, methodType, requestEntity, uriComponentsBuilder);
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR GETTING LIST DATA RESPONSE (BY SPECIFYING HTTP METHOD TYPE) + PARAMETERS + REQUEST BODY AS OBJECT LIST
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @param linkedMultiValueRequestParams - Request Params as LinkedMultiValues
     * @param requestBodyObjectList - Request Body of data list
     * @return - return data list response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    public <T> List<T> list( @NonNull String endPoint, @NonNull Class<T> returnedObjectClass, @NonNull HttpMethod methodType,
                             @RequestParam MultiValueMap<String, @NonNull String> linkedMultiValueRequestParams, @NonNull List<Object> requestBodyObjectList) throws JsonProcessingException {
        HttpEntity<List<Object>> requestEntity = new HttpEntity<>(requestBodyObjectList, headers);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(endPoint)
                .queryParams(linkedMultiValueRequestParams);
        return this.returnedListFromParameterizedRemoteEndpoint(endPoint, returnedObjectClass, methodType, requestEntity, uriComponentsBuilder);
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR COMMUNICATING WITH REMOTE ENDPOINT (USING UriComponentsBuilder) FOR PARAMETERIZED ENDPOINT
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType  - http Method type for remote communication
     * @param requestEntity - Request Entity
     * @param uriComponentsBuilder - Uri Component Builder
     * @return - return data list response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    private <T> List<T> returnedListFromParameterizedRemoteEndpoint(@NonNull String endPoint, @NonNull  Class<T> returnedObjectClass, @NonNull HttpMethod methodType, HttpEntity requestEntity, @NonNull UriComponentsBuilder uriComponentsBuilder) throws JsonProcessingException {
        RestTemplate anyTemplate = new RestTemplate();
        ResponseEntity<List<T>> responseEntity = anyTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                methodType,
                requestEntity,
                new ParameterizedTypeReference<List<T>>() {}
        );
        return responseEntity.getStatusCode().is2xxSuccessful() ? this.getMappedList(responseEntity.getBody(), returnedObjectClass) : Collections.emptyList();
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR COMMUNICATING WITH REMOTE ENDPOINT
     * @param endPoint - end point to remote service
     * @param returnedObjectClass - Object class of the returned data
     * @param methodType - http Method type for remote communication
     * @param requestEntity - Request Entity
     * @return - return data list response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    private <T> List<T> returnedListFromRemoteEndpoint(@NonNull String endPoint, @NonNull  Class<T> returnedObjectClass, @NonNull HttpMethod methodType, HttpEntity requestEntity) throws JsonProcessingException {
        RestTemplate anyTemplate = new RestTemplate();
        ResponseEntity<List<T>> responseEntity = anyTemplate.exchange(
                endPoint,
                methodType,
                requestEntity,
                new ParameterizedTypeReference<List<T>>() {}
        );
        return responseEntity.getStatusCode().is2xxSuccessful() ? this.getMappedList(responseEntity.getBody(), returnedObjectClass) : Collections.emptyList();
    }


    /***----------------------------------------------------------------------------------------------------------------
     * FOR CONVERTING RESPONSE DATA LIST FROM LINKED-HASHMAP LIST TO RESPECTIVE DATA LIST
     * @param genericListFromResponse - Generic Response from remote endpoint
     * @param returnedObjectClass - Object class of the returned data
     * @return  - return data list response
     * @param <T> - Any class
     * @throws JsonProcessingException - Handles exception when fails to process Json Data
    ------------------------------------------------------------------------------------------------------------------*/
    private @NonNull <T> List<T> getMappedList(List<T> genericListFromResponse, Class<T> returnedObjectClass) throws JsonProcessingException {
        List<T> finalList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode nodeDataList =  mapper.readTree(mapper.writeValueAsString(genericListFromResponse));
        nodeDataList.forEach(
                nodeData -> {
                    try {
                        finalList.add(mapper.treeToValue(nodeData, returnedObjectClass));
                    } catch (JsonProcessingException e){
                        throw new RuntimeException(e);
                    }
                }
        );
        return finalList;
    }

}
