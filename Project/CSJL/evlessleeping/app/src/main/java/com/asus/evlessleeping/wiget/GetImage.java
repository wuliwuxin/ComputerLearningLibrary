package com.asus.evlessleeping.wiget;

public class GetImage {

	public static void main(String[] args) {
		String responseContent = HttpClientUtil.getInstance()  
                .sendHttpPost("https://open.ys7.com/api/lapp/device/capture", "accessToken=at.3cb74ctvb5qkmf0y9yfl26h24tycisse-5fhwz9bjmc-0gnlpam-apvdvykmx&deviceSerial=166007633&channelNo=1");  
        System.out.println("reponse content:" + responseContent);

	}

}
