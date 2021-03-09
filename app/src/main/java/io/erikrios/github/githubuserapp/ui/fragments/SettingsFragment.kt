package io.erikrios.github.githubuserapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import io.erikrios.github.githubuserapp.R
import io.erikrios.github.githubuserapp.databinding.FragmentSettingsBinding
import io.erikrios.github.githubuserapp.datastrores.SettingPreferences
import io.erikrios.github.githubuserapp.receivers.AlarmReceiver.Companion.TYPE_REMINDER
import io.erikrios.github.githubuserapp.ui.viewmodels.SettingsViewModel
import io.erikrios.github.githubuserapp.ui.viewmodels.ViewModelFactory
import java.util.*

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val factory = ViewModelFactory(requireContext(), SettingPreferences(requireContext()))
        viewModel = ViewModelProvider(this, factory).get(SettingsViewModel::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleToolbar()
        handleChangeLanguageItem()
        viewModel.isNotificationActive().observe(viewLifecycleOwner) { isActive ->
            binding?.itemSettings?.switchReminder?.isChecked = isActive
            if (isActive) {
                viewModel.setReminderAlarm(
                    requireContext(),
                    TYPE_REMINDER,
                    getString(R.string.app_name),
                    getString(R.string.back_to_app_message)
                )
            } else {
                viewModel.cancelAlarm(requireContext(), TYPE_REMINDER)
            }
        }

        binding?.itemSettings?.switchReminder?.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setNotificationState(isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleToolbar() {
        binding?.toolbar?.apply {
            navigationIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun handleChangeLanguageItem() {
        binding?.apply {
            itemChangeLanguage.tvRecentLanguage.text = Locale.getDefault().displayLanguage
            itemChangeLanguage.clItemChangeLanguage.setOnClickListener { changeLanguage() }
        }
    }

    private fun changeLanguage() {
        val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
        startActivity(intent)
    }
}