package com.wakira.ai.rockscissorspaper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.skplanet.dev.guide.helper.ConverterFactory;
import com.skplanet.dodo.IapPlugin;
import com.skplanet.dodo.IapResponse;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Locale;

public class BuyActivity extends AppCompatActivity {
    class UIHandler extends Handler {
        private final WeakReference mActivity;

        UIHandler(BuyActivity activity) {
            super();
            this.mActivity = new WeakReference(activity);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object v0 = this.mActivity.get();
            if(v0 != null) {
                ((BuyActivity)v0).handleMessage(msg);
            }
        }
    }

    private String AID;
    private ArrayList PIDs;
    Button btnBuy1;
    Button btnBuy5;
    Button btnBuy10;
    Button btnBuy50;
    Button btnBuy100;
    Button btnBuy500;
    Button btnBuy1000;
    Button btnBuy2000;
    Button btnBuy2500;
    Button btnBuy3000;
    Button btnCancel;
    private IapPlugin mPlugin;
    private UIHandler mUIHandler;

    public BuyActivity() {
        super();
        this.AID = "appid=OA00702642";
        this.PIDs = null;
        this.mUIHandler = new UIHandler(this);
    }

    private boolean doPayment(final int PIDindex, int cost, final int addstars) {
        new AlertDialog.Builder(((Context)this)).setMessage(String.format(Locale.getDefault(), "AI를 %d개 구매합니다.\n현금 %d원이 결제됩니다.\n구매하시겠습니까?",
                Integer.valueOf(addstars), Integer.valueOf(cost))).setTitle("AI 구매 확인").setPositiveButton(
                "구매하기", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        BuyActivity.this.requestPayment(PIDindex, addstars);
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(BuyActivity.this, "취소 되었습니다", Toast.LENGTH_SHORT).show();
            }
        }).show();
        return true;
    }

    private void handleMessage(Message msg) {
        if(msg.what == 100) {
            Toast.makeText(((Context)this), (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        this.mPlugin = IapPlugin.getPlugin(((Context)this), getResources().getString(R.string.mode));
        this.PIDs = new ArrayList();
        this.PIDs.add("&product_id=0910053451&product_name=1회");
        this.PIDs.add("&product_id=0910053452&product_name=5회");
        this.PIDs.add("&product_id=0910053453&product_name=10회");
        this.PIDs.add("&product_id=0910053454&product_name=50회");
        this.PIDs.add("&product_id=0910053455&product_name=100회");
        this.PIDs.add("&product_id=0910053456&product_name=500회");
        this.PIDs.add("&product_id=0910053457&product_name=1000회");
        this.PIDs.add("&product_id=0910053458&product_name=2000회");
        this.PIDs.add("&product_id=0910062333&product_name=2500회");
        this.PIDs.add("&product_id=0910062334&product_name=3000회");
        this.btnBuy1 = (Button) this.findViewById(R.id.buy_btnBuy1);
        this.btnBuy5 = (Button) this.findViewById(R.id.buy_btnBuy5);
        this.btnBuy10 = (Button) this.findViewById(R.id.buy_btnBuy10);
        this.btnBuy50 = (Button) this.findViewById(R.id.buy_btnBuy50);
        this.btnBuy100 = (Button) this.findViewById(R.id.buy_btnBuy100);
        this.btnBuy500 = (Button) this.findViewById(R.id.buy_btnBuy500);
        this.btnBuy1000 = (Button) this.findViewById(R.id.buy_btnBuy1000);
        this.btnBuy2000 = (Button) this.findViewById(R.id.buy_btnBuy2000);
        this.btnBuy2500 = (Button) this.findViewById(R.id.buy_btnBuy2500);
        this.btnBuy3000 = (Button) this.findViewById(R.id.buy_btnBuy3000);
        this.btnCancel = (Button) this.findViewById(R.id.buy_btnCancel);
        this.btnBuy1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BuyActivity.this.doPayment(0, 100, 1);
            }
        });
        this.btnBuy5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BuyActivity.this.doPayment(1, 500, 5);
            }
        });
        this.btnBuy10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BuyActivity.this.doPayment(2, 1000, 10);
            }
        });
        this.btnBuy50.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BuyActivity.this.doPayment(3, 5000, 50);
            }
        });
        this.btnBuy100.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BuyActivity.this.doPayment(4, 10000, 100);
            }
        });
        this.btnBuy500.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BuyActivity.this.doPayment(5, 50000, 500);
            }
        });
        this.btnBuy1000.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BuyActivity.this.doPayment(6, 100000, 1000);
            }
        });
        this.btnBuy2000.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BuyActivity.this.doPayment(7, 200000, 2000);
            }
        });
        this.btnBuy2500.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BuyActivity.this.doPayment(8, 250000, 2500);
            }
        });
        this.btnBuy3000.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BuyActivity.this.doPayment(9, 300000, 3000);
            }
        });
        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(BuyActivity.this, "구매를 취소하셨습니다.", Toast.LENGTH_SHORT).show();
                BuyActivity.this.setResult(1);
                BuyActivity.this.finish();
            }
        });
    }

    protected void onPause() {
        this.mPlugin.exit();
        super.onPause();
    }

    private boolean requestPayment(int PIDindex, final int addstars) {
        this.mPlugin.sendPaymentRequest(String.valueOf(this.AID) + this.PIDs.get(PIDindex), new IapPlugin.RequestCallback
                () {
            public void onError(String requestid, String errcode, String errmsg) {
                BuyActivity.this.mUIHandler.obtainMessage(100, "구매 에러:" + requestid + " \ncode:" + errcode
                        + " \nmsg:" + errmsg).sendToTarget();
            }

            public void onResponse(IapResponse data) {
                int v10 = 100;
                if(data == null || data.getContentLength() <= 0) {
                    BuyActivity.this.mUIHandler.obtainMessage(v10, "구매 서버의 응답 이상 : \n응답 메시지가 비었음").sendToTarget();
                }
                else if("0000".equals(ConverterFactory.getConverter().fromJson(data.getContentToString())
                        .result.code)) {
                    BuyActivity.this.mUIHandler.obtainMessage(v10, "구매 완료").sendToTarget();
                    Intent v1 = new Intent();
                    v1.putExtra("AI", addstars);
                    BuyActivity.this.setResult(200, v1);
                    BuyActivity.this.finish();
                }
            }
        });
        return true;
    }
}
