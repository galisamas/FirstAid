package com.itworks.firstaid.repositories;

import com.itworks.firstaid.models.HospitalListItem;

import java.util.ArrayList;
import java.util.List;

public class HospitalRepository {

    private List<HospitalListItem> hospitalList;

    public HospitalRepository() {
        hospitalList = new ArrayList<>();
        createList();
    }

    private void createList() {
        hospitalList.add(new HospitalListItem("Regionin� Vilniaus universitetin� ligonin�","�iltnami� g. 23 (15 km)","(85) 216 9069", "www.rvul.lt", 54.665, 25.44));
        hospitalList.add(new HospitalListItem("Santari�ki� klinikos","Santari�ki� g. 2 (35 km)","(85) 216 9069", "www.rvul.lt", 54.665, 25.44));
    }

    public List<HospitalListItem> getHospitalList() {
        return hospitalList;
    }
}
