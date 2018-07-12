package com.cesaas.android.order.base;

/**
 * Author FGB
 * Description
 * Created 2017/4/9 21:27
 * Version 1.0
 */
public class BaseBean {

    private long id;
    private boolean IsSuccess;
    private boolean IsNotSuccess;
    private String Message;
    private String ResultNo;
    private int RecordCount;
    private int PageCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setSuccess(boolean success) {
        IsSuccess = success;
    }

    public boolean isNotSuccess() {
        return IsNotSuccess;
    }

    public void setNotSuccess(boolean notSuccess) {
        IsNotSuccess = notSuccess;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getResultNo() {
        return ResultNo;
    }

    public void setResultNo(String resultNo) {
        ResultNo = resultNo;
    }

    public int getRecordCount() {
        return RecordCount;
    }

    public void setRecordCount(int recordCount) {
        RecordCount = recordCount;
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int pageCount) {
        PageCount = pageCount;
    }
}
