package nse.services.open.interest.service;

import com.connector.nse.openinterest.client.OpenInterestClient;
import nse.services.open.interest.repository.OpenInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenInterestService {

    @Autowired
    private OpenInterestRepository openInterestRepository;

    private OpenInterestClient openInterestCaller;
}
