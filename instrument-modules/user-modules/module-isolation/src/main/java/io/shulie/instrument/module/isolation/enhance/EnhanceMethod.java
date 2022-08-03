package io.shulie.instrument.module.isolation.enhance;

import io.shulie.instrument.module.isolation.common.ResourceInit;
import io.shulie.instrument.module.isolation.proxy.ShadowMethodProxy;

/**
 * @author Licey
 * @date 2022/8/1
 */
public class EnhanceMethod {
    private String method;
    private Class[] argTypes;

    private ResourceInit<ShadowMethodProxy> methodProxyInit;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Class[] getArgTypes() {
        return argTypes;
    }

    public void setArgTypes(Class[] argTypes) {
        this.argTypes = argTypes;
    }

    public ResourceInit<ShadowMethodProxy> getMethodProxyInit() {
        return methodProxyInit;
    }

    public void setMethodProxyInit(ResourceInit<ShadowMethodProxy> methodProxyInit) {
        this.methodProxyInit = methodProxyInit;
    }
}
