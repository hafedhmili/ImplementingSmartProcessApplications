package spa.samples.heartpatientmonitoring.backend.types;

import com.microsoft.azure.sdk.iot.service.IotHubServiceClientProtocol;

public record IoTHubBinding(String connectionString, IotHubServiceClientProtocol protocol) {
    
}
