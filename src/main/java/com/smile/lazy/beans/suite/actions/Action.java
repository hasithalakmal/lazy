package com.smile.lazy.beans.suite.actions;

import com.smile.lazy.beans.enums.ActionTypeEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class Action implements Serializable {

    private Integer actionId;
    private Integer actionSequenceId;
    private String actionName;
    private String actionDescription;
    private Boolean active;
    private ActionTypeEnum actionType;

    public Action(ActionTypeEnum actionType) {
        this.actionType = actionType;
    }

    public Action(Integer actionId, ActionTypeEnum actionType) {
        this.actionId = actionId;
        this.actionType = actionType;
    }

    public Action(Integer actionId, Integer actionSequenceId, ActionTypeEnum actionType) {
        this.actionId = actionId;
        this.actionSequenceId = actionSequenceId;
        this.actionType = actionType;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getActionSequenceId() {
        return actionSequenceId;
    }

    public void setActionSequenceId(Integer actionSequenceId) {
        this.actionSequenceId = actionSequenceId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public ActionTypeEnum getActionType() {
        return actionType;
    }

    public void setActionType(ActionTypeEnum actionType) {
        this.actionType = actionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Action action = (Action) o;

        return new EqualsBuilder()
              .append(actionId, action.actionId)
              .append(actionSequenceId, action.actionSequenceId)
              .append(actionName, action.actionName)
              .append(actionDescription, action.actionDescription)
              .append(active, action.active)
              .append(actionType, action.actionType)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(actionId)
              .append(actionSequenceId)
              .append(actionName)
              .append(actionDescription)
              .append(active)
              .append(actionType)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "Action{" +
              "actionId=" + actionId +
              ", actionSequenceId=" + actionSequenceId +
              ", actionName='" + actionName + '\'' +
              ", actionDescription='" + actionDescription + '\'' +
              ", active=" + active +
              ", actionType=" + actionType +
              '}';
    }
}
