package wissen.dhrss;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.nodes.Element;

public class HaberGoster extends AppCompatActivity {

    Element haber = Data.secilenHaber;
    TextView tvBaslik, tvTarih, tvAciklama;
    ImageView iv;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haber_goster);

        tvBaslik = (TextView) findViewById(R.id.tvBaslik);
        tvTarih = (TextView) findViewById(R.id.tvTarih);
        tvAciklama = (TextView) findViewById(R.id.tvAciklama);
        iv = (ImageView) findViewById(R.id.iv);

        String resimAdr = haber.select("enclosure").first().attr("url");

        String tmp = haber.select("description").first().text();
        int ndx = tmp.indexOf("/>");
        tmp = tmp.substring(ndx+2);
        tvBaslik.setText(haber.select("title").text());
        tvTarih.setText(haber.select("pubDate").text());
        tvAciklama.setText(tmp);
        // Picasso Ile ASYNC Resim
        Picasso.with(HaberGoster.this)
                .load(resimAdr)

                .placeholder(R.drawable.bekle)
                .error(android.R.drawable.ic_menu_delete)
                .into(iv);
    }

    public void paylas(View v)
    {
        String link = haber.select("guid").text();

        Intent i = new Intent(Intent.ACTION_SEND, Uri.parse(link));
        i.setType("*/*");
        i.putExtra(Intent.EXTRA_TEXT, link);
        //i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));
       // startActivity(i);
    }

    public void oku(View v)
    {
        String link = haber.select("guid").text();
        // Android Web Intent
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(i);
    }
}
