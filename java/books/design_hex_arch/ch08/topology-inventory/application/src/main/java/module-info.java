module application {
    requires domain;
    requires static lombok;

    provides hex.arch.topologyinventory.application.use_case.RouterManagementUseCase
        with hex.arch.topologyinventory.application.port.in.RouterManagementInputPort;
    provides hex.arch.topologyinventory.application.use_case.SwitchManagementUseCase
        with hex.arch.topologyinventory.application.port.in.SwitchManagementInputPort;
    provides hex.arch.topologyinventory.application.use_case.NetworkManagementUseCase
        with hex.arch.topologyinventory.application.port.in.NetworkManagementInputPort;

    exports hex.arch.topologyinventory.application.port.in;
    exports hex.arch.topologyinventory.application.port.out;
    exports hex.arch.topologyinventory.application.use_case;

}
