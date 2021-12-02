package com.example.films.ui.contacts

import android.Manifest
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.films.R
import com.example.films.databinding.ContactsFragmentBinding

class ContactsFragment : Fragment(R.layout.contacts_fragment) {

    private var _binding: ContactsFragmentBinding? = null
    private val binding get() = _binding!!

    private val permissionResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                getContacts()
            } else {
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.permisson_to_read_contacts)
                    .setMessage(R.string.read_contacts_permission_explanation)
                    .setPositiveButton(R.string.grant_access) { _, _ ->
                        requestPermission()
                    }
                    .setNegativeButton(R.string.do_not_grant_access) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ContactsFragmentBinding.bind(view)

        checkPermission()
    }

    private fun checkPermission() {
        context?.let { notNullContext ->
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    notNullContext,
                    Manifest.permission.READ_CONTACTS
                ) -> {
                    getContacts()
                }
                else -> {
                    requestPermission()
                }
            }
        }
    }

    private fun requestPermission() {
        permissionResult.launch(Manifest.permission.READ_CONTACTS)
    }

    private fun getContacts() {
        val context = context ?: return

        val contentResolver: ContentResolver = context.contentResolver

        val cursorWithContacts: Cursor =
            contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            ) ?: return

        cursorWithContacts.let { cursor ->
            for (i in 0..cursor.count) {
                if (cursor.moveToPosition(i)) {
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    addView(context, name)
                }
            }
        }

        cursorWithContacts.close()
    }

    private fun addView(context: Context, textToShow: String) {
        binding.containerForContacts.addView(
            AppCompatTextView(context).apply {
                text = textToShow
                textSize = resources.getDimension(R.dimen.size_normal)
            }
        )
    }
}