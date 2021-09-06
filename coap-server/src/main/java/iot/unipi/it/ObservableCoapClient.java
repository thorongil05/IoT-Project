package iot.unipi.it;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import iot.unipi.it.model.Resource;
import iot.unipi.it.model.TrackLimit;

public class ObservableCoapClient extends CoapClient {
	
	private TrackLimit res;
	CoapObserveRelation coapObserveRelation;
	
	public ObservableCoapClient(TrackLimit res) {
		super(res.getCoapURI());
		this.res= res;
	}
	
	public Resource getRes() {
		return res;
	}
	
	public void startObserving() {
		this.coapObserveRelation = this.observe(new CoapHandler() {
			
			public void onLoad(CoapResponse response) {
				String content = response.getResponseText();
				try {
					JSONObject jsonOb = (JSONObject) JSONValue.parseWithException(content);
					if (jsonOb.containsKey("crossed")) {
						System.out.println(content);
						boolean value = Boolean.parseBoolean(jsonOb.get("crossed").toString());
						String message = value ? "limit exceeded" : "limit not exceeded";
						System.out.println("Res: " + res.getName() + " --> " + message);
					}
				} catch (ParseException e) {
					System.out.println("Error: " + e.getMessage());
				}
			}
			
			public void onError() {
				System.out.println("--------ERROR DURING OBSERVING RESOURCE--------");
			}
		});
		System.out.println("Observing Mode ON for tracklimit: " + res.getName());
	}
	
	public void stopObserving() {
		this.coapObserveRelation.reactiveCancel();
		System.out.println("Observing Mode OFF for tracklimit: " + res.getName());
	}
	
	
}