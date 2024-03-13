package org.redhat.demo.crazytrain.processor;

import org.jboss.logging.Logger;

import java.net.URI;
import java.util.Calendar;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.camel.Exchange;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Message;

import io.smallrye.reactive.messaging.ce.OutgoingCloudEventMetadata;
import io.cloudevents.CloudEvent;
import io.cloudevents.SpecVersion;
import io.cloudevents.core.builder.CloudEventBuilder;
import java.net.URI;
import java.util.UUID;
import org.apache.camel.Processor;
import org.apache.camel.console.DevConsole.MediaType;

@Named("CloudEventProcessor") 
@ApplicationScoped
public class CloudEventProcessor implements Processor{
    private static final Logger LOGGER = Logger.getLogger(CloudEventProcessor.class);
    @Override
    public void process(Exchange exchange) throws Exception {
        String message = exchange.getIn().getBody().toString();
       // System.out.println("Received : "+message);
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        JsonNode no = null;
        ObjectNode node = null;
		try {
			jsonNode = mapper.readTree(message);
            //LOGGER.infof("Json Node  : '%s'",jsonNode.toString());
            ObjectMapper mp = new ObjectMapper();
            node = mp.createObjectNode()
                .put("id", UUID.randomUUID().toString())
                .put("specversion", "1.0")
                .put("source", "http://example.com")
                .put("type", "result")
                .put("subject", "result-message")
                .put("time", Calendar.getInstance().getTime().toString())
                .put("datacontenttype", "application/json");
            no = node.set("data", jsonNode);
        } catch (Exception e) {
            LOGGER.error("Error processing message", e);
        }
        //LOGGER.infof("CloudEvent : '%s'",no.toString());

        exchange.getIn().setBody(no.toString());

    }
}