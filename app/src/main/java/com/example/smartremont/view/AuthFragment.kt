package com.example.smartremont.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.smartremont.R
import com.example.smartremont.databinding.FragmentAuthBinding
import com.example.smartremont.viewmodel.AuthFragmentViewModel


class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding?= null
    private val binding get() = _binding!!
    private var email: String?= null
    private var password: String? = null

    private lateinit var viewModel: AuthFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAuthBinding.inflate(inflater, container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this,this.defaultViewModelProviderFactory).get(AuthFragmentViewModel::class.java)


        binding.loginProgressBar.visibility = View.GONE
        binding.loginSignInButton.setOnClickListener {
            email = binding.loginEmailText.text.toString()
            password = binding.loginPasswordText.text.toString()
            if(email.equals("") && password.equals("")){
                Toast.makeText(requireContext(),"Email and Password didn't editted",Toast.LENGTH_LONG).show()
            }else if(email.equals("")){
            Toast.makeText(requireContext(),"Email didn't editted",Toast.LENGTH_LONG).show()
            }else if(password.equals("")){
                Toast.makeText(requireContext(),"Password didn't editted",Toast.LENGTH_LONG).show()
            }else{
                binding.loginProgressBar.visibility = View.VISIBLE
                viewModel.signIn(email!!, password!!)
                observeData()
            }
        }
    }

    private fun observeData(){
        viewModel.signedIn.observe(viewLifecycleOwner, Observer {signedIn->
            signedIn.let {
                binding.loginProgressBar.visibility = View.GONE
                if(it){
                   val action = AuthFragmentDirections.actionAuthFragmentToFeedFragment()
                   Navigation.findNavController(requireView()).navigate(action)
                }else{
                    binding.loginEmailText.setText("")
                    binding.loginPasswordText.setText("")
                }
            }
        })
        viewModel.signedInLoading.observe(viewLifecycleOwner, Observer { signedInLoading->
            signedInLoading.let {
                if(it){
                    binding.loginProgressBar.visibility = View.VISIBLE
                }else{
                    binding.loginProgressBar.visibility = View.GONE
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}