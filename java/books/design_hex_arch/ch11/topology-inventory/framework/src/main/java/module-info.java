module framework {

    requires domain;
    requires application;

    requires static lombok;
    requires jakarta.persistence;
    requires jakarta.enterprise.cdi.api;
    requires jakarta.inject.api;
    requires org.eclipse.persistence.core;

    uses hex.arch.topologyinventory.application.use_case.RouterManagementUseCase;
    uses hex.arch.topologyinventory.application.use_case.SwitchManagementUseCase;
    uses hex.arch.topologyinventory.application.use_case.NetworkManagementUseCase;
    uses hex.arch.topologyinventory.application.port.out.RouterManagementOutputPort;
    uses hex.arch.topologyinventory.application.port.out.SwitchManagementOutputPort;

    provides hex.arch.topologyinventory.application.port.out.RouterManagementOutputPort
        with hex.arch.topologyinventory.framework.adapter.out.h2.RouterManagementH2OutputAdapter;

    provides hex.arch.topologyinventory.application.port.out.SwitchManagementOutputPort
        with hex.arch.topologyinventory.framework.adapter.out.h2.SwitchManagementH2OutputAdapter;

    opens hex.arch.topologyinventory.framework.adapter.out.h2.data;

    exports hex.arch.topologyinventory.framework.adapter.out.h2.data;
    exports hex.arch.topologyinventory.framework.adapter.in;

}
