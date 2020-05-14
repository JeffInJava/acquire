package org.jeff.acquire.pojo;

import java.util.List;

public class AfbData {
    private String total;
    private List<Rows> rows;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Rows> getRows() {
        return rows;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }
}
