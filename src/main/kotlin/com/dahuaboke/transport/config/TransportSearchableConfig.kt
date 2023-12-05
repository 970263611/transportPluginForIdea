package com.dahuaboke.transport.config

import com.dahuaboke.transport.view.ConfigurationView
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.options.SearchableConfigurable
import com.intellij.openapi.util.NlsContexts.ConfigurableName
import org.jetbrains.annotations.NonNls
import javax.swing.JComponent

/**
 * author: dahua
 * date: 2023/12/5 14:13
 */
class TransportSearchableConfig : SearchableConfigurable {
    private val configurationView = ConfigurationView()
    override fun getId(): @NonNls String {
        return configurationView.getId()
    }

    override fun getDisplayName(): @ConfigurableName String? {
        return configurationView.getDisplayName()
    }

    override fun createComponent(): JComponent? {
        return configurationView.getTransportSquare()
    }

    override fun isModified(): Boolean {
        return false
    }

    @Throws(ConfigurationException::class)
    override fun apply() {
    }
}
