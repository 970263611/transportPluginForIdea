package com.dahuaboke.transport.view

import javax.swing.*

/**
 * author: dahua
 * date: 2023/12/5 17:22
 */
class ConfigurationView {

    private final var ID: String = "DAHUA_TRANSPORT_CONFIGURATION_VIEW"
    private final var DISPLAY_NAME: String = "大花视图配置"

    private var transportSquare: JTabbedPane? = null
    private var baiduSquare: JPanel? = null
    private var appIdInput: JTextField? = null
    private var appIdText: JLabel? = null
    private var secretInput: JPasswordField? = null
    private var secretText: JLabel? = null
    private var testButton: JButton? = null
    private fun createUIComponents() {
        // TODO: place custom component creation code here
    }

    fun getId() = ID
    fun getDisplayName() = DISPLAY_NAME
    fun getTransportSquare() = transportSquare
}
