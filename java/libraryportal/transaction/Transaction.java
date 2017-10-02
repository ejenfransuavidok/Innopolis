package libraryportal.transaction;

import libraryportal.response.Response;
import libraryportal.request.Request;

public interface Transaction {

    Response sendRequest(Request request);

}
