package com.example.budalajedna.nfctryout.presentation.Share;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class ShareFragmentBinding extends ViewDataBinding {
    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }


    public abstract void setVm(@Nullable ShareViewModel gameViewModel);
    protected ShareFragmentBinding(android.databinding.DataBindingComponent _bindingComponent, View _root, int _localFieldCount){
        super(_bindingComponent, _root, _localFieldCount);

    }


    @Override
    public boolean setVariable(int variableId, @Nullable Object value) {
        return false;
    }

    @Override
    protected void executeBindings() {

    }

    @Override
    public void invalidateAll() {

    }

    @Override
    public boolean hasPendingBindings() {
        return false;
    }
}
