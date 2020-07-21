package com.zlgspace.timemonitor;


class TimeTick {
    private String actionName;
    private long actionTime;

    public TimeTick(){
    }

    public TimeTick(String actionName,long actionTime){
        setActionName(actionName);
        setActionTime(actionTime);
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public long getActionTime() {
        return actionTime;
    }

    public void setActionTime(long actionTime) {
        this.actionTime = actionTime;
    }

    @Override
    public String toString() {
        return "{ "+actionName+": "+actionTime+"ms }";
    }
}
