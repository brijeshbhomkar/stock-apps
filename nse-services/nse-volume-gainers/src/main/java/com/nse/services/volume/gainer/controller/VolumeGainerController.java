package com.nse.services.volume.gainer.controller;

import com.common.exception.ApplicationException;
import com.nse.services.volume.gainer.model.VolumeGainer;
import com.nse.services.volume.gainer.service.VolumeGainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nse/volume")
public class VolumeGainerController {

    @Autowired
    private VolumeGainerService volumeGainerService;

    @GetMapping("/test")
    public String test() {
        return "I am running ok!!";
    }

    @GetMapping("/nifty/100")
    public List<VolumeGainer> volumeGainerNifty100() throws ApplicationException {
        return volumeGainerService.getVolumeGainersNifty100();
    }

    @GetMapping("/nifty/500")
    public List<VolumeGainer> volumeGainerNifty500() throws ApplicationException {
        return volumeGainerService.getVolumeGainersNifty100();
    }

    @GetMapping("/smcp/100")
    public List<VolumeGainer> volumeGainerSMCP100() throws ApplicationException {
        return volumeGainerService.getVolumeGainersSMCP100();
    }

}
