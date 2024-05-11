package com.sr.Services;

import com.sr.Entities.ExternalApi;

public interface ExternalApiService {

    ExternalApi externalGetCall();

    ExternalApi externalPostCall(ExternalApi requestBody);
}
