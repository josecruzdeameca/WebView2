package jose.cruz.webview2


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.webkit.WebViewClient
import android.graphics.Bitmap









// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragmento_1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragmento_1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // declaracion de widgets
    var et_url: EditText? = null
    var b_ir: Button? = null
    var wv_1: WebView? = null
    var pb_1: ProgressBar? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
            val miVista = inflater.inflate(R.layout.fragment_fragmento_1, container, false)

        // instanciacion de widgets
        et_url = miVista.findViewById(R.id.et_url)
        b_ir = miVista.findViewById(R.id.b_ir)
        wv_1 = miVista.findViewById(R.id.wv_1)
        pb_1 = miVista.findViewById(R.id.pb_1)

        //wv_1!!.webChromeClient = WebChromeClient()
        wv_1!!.webChromeClient = object: WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                pb_1!!.progress = newProgress
            }

        }
        wv_1!!.settings.javaScriptEnabled = true


        b_ir!!.setOnClickListener {
            val url = et_url!!.text.toString().trim()
            var url_final = ""
            if( url.startsWith("www") ){
                url_final = "https://$url"
            } else {
                url_final = "https://www.google.com/search?q=$url"
            }
            //wv_1!!.webViewClient = WebViewClient()
            //esta linea es suficiente si no se desea actualizar la url en TextView

            wv_1!!.setWebViewClient(object : WebViewClient() {

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)

                    val webUrl = wv_1!!.getUrl()
                    et_url!!.setText(webUrl)
                }

            })

            wv_1!!.loadUrl(url_final)

        }

        return miVista

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragmento_1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragmento_1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }




}
