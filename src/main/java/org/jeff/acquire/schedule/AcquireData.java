package org.jeff.acquire.schedule;

import lombok.extern.slf4j.Slf4j;
import org.jeff.acquire.dao.AccmalfunctionRepository;
import org.jeff.acquire.entity.Accmalfunction;
import org.jeff.acquire.pojo.AccmalfunctionBean;
import org.jeff.acquire.pojo.AfbData;
import org.jeff.acquire.pojo.Rows;
import org.jeff.acquire.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import javax.xml.ws.Response;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class AcquireData {
    @Autowired
    private AccmalfunctionRepository afr;

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "0/30 * * * * ?")
    @Transactional
    public void fetchDate(){
        String sdate = DateUtils.dateToStr(new Date());

        afr.deleteBySdate(sdate);

        AccmalfunctionBean afb = this.get(sdate);
        Accmalfunction af = null;
        AfbData ada = afb.getData();
        List<Rows> lstrow = ada.getRows();
        for(Rows r:lstrow){
            af = new Accmalfunction();
            af.setMid(r.getId());
            af.setSdate(sdate);
            af.setSn(r.getSn());
            af.setCreatedate(r.getCreated());
            af.setLabels(r.getLabel());
            af.setProgress(r.getProgress());
            af.setReportor(r.getReportor());
            af.setOrderindex(r.getOrderIndex());
            af.setAddtime(new Date());
            afr.save(af);
        }
    }

    public AccmalfunctionBean get(String sdate){
        ResponseEntity<AccmalfunctionBean> entity = restTemplate.getForEntity("http://172.16.98.52:18222/sbwx/current?key=8f56de7e-707e-4107-a202-9f8c126c6ebd&today={sdate}",AccmalfunctionBean.class,sdate);
        return entity.getBody();
    }
}
