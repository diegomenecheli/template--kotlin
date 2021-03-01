package com.templateapp.template.services.actions

import android.app.Activity
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.template_android.R
import java.util.*
import java.util.regex.Pattern

object Validation {

    private val PATTERN_GENERIC = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")
    private val PATTERN_NUMBERS =
        Pattern.compile("(?=^((?!((([0]{11})|([1]{11})|([2]{11})|([3]{11})|([4]{11})|([5]{11})|([6]{11})|([7]{11})|([8]{11})|([9]{11})))).)*$)([0-9]{11})")


    // validating gender
//    fun isValidGender(context: Activity, man: Boolean, woman: Boolean): Boolean {
//        if (man || woman) {
//            return true
//        }
//        Toast.makeText(
//            context,
//            context.resources.getString(R.string.validation_of_terms_of_service),
//            Toast.LENGTH_LONG
//        ).show()
//        return false
//    }

    // validating password
    fun isValidPassword(context: Activity, pass: String?): Boolean {
        if (pass != null && pass.length > 5) {
            return true
        }
        if (pass == null || pass.isEmpty() || pass.trim { it <= ' ' } == "") {
            Toast.makeText(
                context,
                context.resources.getString(R.string.validation_no_password),
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        Toast.makeText(
            context,
            context.resources.getString(R.string.validation_invalid_password),
            Toast.LENGTH_LONG
        ).show()
        return false
    }

    // Validating retype passaword
    fun isValidPasswordConfirmation(context: Activity, pass: String?, password: String?): Boolean {
        if( pass != password){
            Toast.makeText(
                context,
                context.resources.getString(R.string.validation_wrong_match),
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        if (pass != null && pass.length > 5) {
            return true
        }
        if (pass == null || pass.isEmpty() || pass.trim { it <= ' ' } == "") {
            Toast.makeText(
                context,
                context.resources.getString(R.string.validation_no_password),
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        Toast.makeText(
            context,
            context.resources.getString(R.string.validation_invalid_password),
            Toast.LENGTH_LONG
        ).show()
        return false
    }

    // validating name
    fun isValidUserName(context: Activity, name: String?): Boolean {
        if (name != null && name.trim { it <= ' ' } != "") {
            return true
        }
        Toast.makeText(
            context,
            context.resources.getString(R.string.validation_no_name),
            Toast.LENGTH_LONG
        ).show()
        return false
    }

    // validating conversation
    fun isValidUserConversation(context: Activity, text: String?): Boolean {
        if (text != null && text.trim { it <= ' ' } != "") {
            return true
        }
        return false
    }

    // validating number card
    fun isValidCardNumber(context: Activity, card: String?): Boolean {
        if (card != null && card.length == 19) {
            return true
        }
        Toast.makeText(
            context,
            context.resources.getString(R.string.validation_invalid_card),
            Toast.LENGTH_LONG
        ).show()
        return false
    }

    // validating cvv
    fun isValidCVV(context: Activity, cvv: String?): Boolean {
        if (cvv != null && cvv.length == 3) {
            return true
        }
        Toast.makeText(
            context,
            context.resources.getString(R.string.validation_invalid_cvv),
            Toast.LENGTH_LONG
        ).show()
        return false
    }

    // validating validity
    fun isValidValidity(context: Activity, validity: String?): Boolean {
        if (validity != null && validity.length == 4 && validity.subSequence(0, 2).toString().toInt() <= 12 && validity.subSequence(2, 4).toString().toInt() >= 20) {
            return true
        }
        Toast.makeText(
            context,
            context.resources.getString(R.string.validation_invalid_date),
            Toast.LENGTH_LONG
        ).show()
        return false
    }

    // validating rating
    fun isValidRatingBar(context: Activity, rating: String): Boolean {
        if (rating.trim { it <= ' ' } != "0.0") {
            return true
        }
        Toast.makeText(
            context,
            context.resources.getString(R.string.validation_no_rating),
            Toast.LENGTH_LONG
        ).show()
        return false
    }

    //validation cpf
    fun isValidCPF(context: Activity, cpf: String?): Boolean {
        var cpf = cpf
        if (cpf != null && PATTERN_GENERIC.matcher(cpf).matches()) {
            cpf = cpf.replace("-|\\.".toRegex(), "")
            if (PATTERN_NUMBERS.matcher(cpf).matches()) {
                val numbers = IntArray(11)
                for (i in 0..10) numbers[i] = Character.getNumericValue(cpf[i])
                var i: Int
                var sum = 0
                var factor = 100
                i = 0
                while (i < 9) {
                    sum += numbers[i] * factor
                    factor -= 10
                    i++
                }
                var leftover = sum % 11
                leftover = if (leftover == 10) 0 else leftover
                if (leftover == numbers[9]) {
                    sum = 0
                    factor = 110
                    i = 0
                    while (i < 10) {
                        sum += numbers[i] * factor
                        factor -= 10
                        i++
                    }
                    leftover = sum % 11
                    leftover = if (leftover == 10) 0 else leftover
                    if (leftover != numbers[10]) {
                        Toast.makeText(
                            context,
                            context.resources.getString(R.string.validation_invalid_cpf),
                            Toast.LENGTH_LONG
                        ).show()
                        return false
                    } else
                        return true
                }
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.validation_invalid_cpf),
                    Toast.LENGTH_LONG
                ).show()
                return false
            }
        }

        if (cpf == null || cpf.isEmpty()) {
            Toast.makeText(
                context,
                context.resources.getString(R.string.validation_no_cpf),
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        if (!PATTERN_GENERIC.matcher(cpf).matches()) {
            Toast.makeText(
                context,
                context.resources.getString(R.string.validation_format_cpf),
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        Toast.makeText(
            context,
            context.resources.getString(R.string.validation_invalid_cpf),
            Toast.LENGTH_LONG
        ).show()
        return false
    }

    //validation transport type spinner
    fun isValidTrasport(context: Activity, position: Int, withdraw: Boolean?): Boolean {
        if (withdraw!! && position == 0) {
            Toast.makeText(
                context,
                context.resources.getString(R.string.validation_no_trasport),
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        return true
    }

    //validation email
    fun isValidEmail(context: Activity, target: CharSequence): Boolean {
        if (TextUtils.isEmpty(target)) {
            Toast.makeText(
                context,
                context.resources.getString(R.string.validation_no_email),
                Toast.LENGTH_LONG
            ).show()
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()) {
            Toast.makeText(
                context,
                context.resources.getString(R.string.validation_invalid_email),
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    //validation phone
    fun isValidPhoneNumber(context: Activity, phone: String): Boolean {
        if (phone.trim { it <= ' ' } != "" && phone.length < 15) {
            Toast.makeText(
                context,
                context.resources.getString(R.string.validation_invalid_phone),
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        if (phone.trim { it <= ' ' } != "" && phone.length > 14 && !Patterns.PHONE.matcher(phone).matches()) {
            Toast.makeText(
                context,
                context.resources.getString(R.string.validation_invalid_phone),
                Toast.LENGTH_LONG
            ).show()
            return false
        }

        if (phone.trim { it <= ' ' } != "" && phone.length > 14 && Patterns.PHONE.matcher(phone).matches()) {
            return true
        }
        Toast.makeText(
            context,
            context.resources.getString(R.string.validation_no_phone),
            Toast.LENGTH_LONG
        ).show()
        return false
    }

    // validating complement
    fun isValidComplement(context: Activity, complement: String, check: Boolean): Boolean {
        if (check || complement.length > 0) {
            return true
        }
        Toast.makeText(
            context,
            context.resources.getString(R.string.validation_no_complement),
            Toast.LENGTH_LONG
        ).show()
        return false
    }

    // validating cep
    fun isValidCep(context: Activity, cep: String): Boolean {
        if (cep.length == 9) {
            return true
        }
        Toast.makeText(
            context,
            context.resources.getString(R.string.validation_no_cep),
            Toast.LENGTH_LONG
        ).show()
        return false
    }

    // validating complement
    fun isValidBirthday(context: Activity, day: String): Boolean {
        if(day.length == 10){
            val sDay: Int = day.substring(0, 2).toInt()
            val sMonth: Int = day.substring(3, 5).toInt()
            val sYear: Int = day.substring(6, 10).toInt()
            if (sDay in 1..31 && sMonth in 1..12 && sYear in 1900..2020) {
                return true
            }
        }
        Toast.makeText(
            context,
            context.resources.getString(R.string.validation_invalid_date),
            Toast.LENGTH_LONG
        ).show()
        return false
    }


    // validating instruction
    fun isValidInstruction(context: Activity, instruction: String): Boolean {
        if (instruction.length > 0 && instruction.length < 10) {
            Toast.makeText(
                context,
                context.resources.getString(R.string.validation_more_instructions),
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        if (instruction.length >= 10) {
            return true
        }
        Toast.makeText(
            context,
            context.resources.getString(R.string.validation_no_instructions),
            Toast.LENGTH_LONG
        ).show()
        return false
    }
}
