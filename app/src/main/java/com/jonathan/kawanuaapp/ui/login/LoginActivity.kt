package com.jonathan.kawanuaapp.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.jonathan.kawanuaapp.ui.main.MainActivity
import com.jonathan.kawanuaapp.R
import com.jonathan.kawanuaapp.databinding.ActivityLoginBinding
import com.jonathan.kawanuaapp.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
           .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = Firebase.auth

        binding.buttonDaftarGoogle.setOnClickListener {
            signIn()
        }

        binding.instagram.setOnClickListener {
            openInstagram()
        }

        binding.linkedin.setOnClickListener {
            openLinkedin()
        }

        binding.tvDaftar.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        playAnimation()
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    companion object {
        const val TAG = "Login Activity"
    }

    private fun openInstagram() {
        val instagramPackage = "com.instagram.android"
        val uri = Uri.parse("https://www.instagram.com/kawanua.app/")

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = uri
        intent.setPackage(instagramPackage)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            val webIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/kawanua.app/"))
            startActivity(webIntent)
        }
    }

    private fun openLinkedin() {
        val linkedInProfileUrl = "https://www.linkedin.com/company/kawanuntukalam"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedInProfileUrl))

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedInProfileUrl))
            startActivity(webIntent)
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.logoKawanua, View.TRANSLATION_X, -60f, 60f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(1000)

        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(1000)

        AnimatorSet().apply {
            playSequentially(
                emailEditTextLayout,
                passwordEditTextLayout,
            )
            startDelay = 100
        }.start()
    }
}