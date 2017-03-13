package com.android.pet.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ScheduleOfClasses;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.doepiccoding.navigationdrawer.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NavigationActivity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;
	ImageView home;
	Fragment fragment = null;
	TextView appname;
	ExpandableListView expListView;
	HashMap<String, List<String>> listDataChild;
	ExpandableListAdapter listAdapter;
	List<String> listDataHeader;
	private WebView mwebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//String fontPath = "fonts/Shadow Boxing.ttf";
		setContentView(R.layout.activity_navigation);
		home = (ImageView)findViewById(R.id.home);
		home.setOnClickListener(homeOnclickListener);
		appname = (TextView)findViewById(R.id.appname);
		//Typeface tf = Typeface.createFromAsset(this.getAssets(), fontPath);
		//appname.setTypeface(tf);
		setUpDrawer();
		mwebView = (WebView) findViewById(R.id.myWebView);
		WebSettings webSettings = mwebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		//improve wevView performance
		mwebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
		mwebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		mwebView.getSettings().setAppCacheEnabled(true);
		mwebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		webSettings.setDomStorageEnabled(true);
		webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
		webSettings.setUseWideViewPort(true);
		webSettings.setSavePassword(true);
		webSettings.setSaveFormData(true);
		//webSettings.setEnableSmoothTransition(true);
		mwebView.loadUrl("file:///android_asset/mynpu3.html");
		mwebView.setWebViewClient(new MyWebviewClient());

	}

	/**
	 *
	 * Get the names and icons references to build the drawer menu...
	 */
	private void setUpDrawer() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));
		mDrawerLayout.setDrawerListener(mDrawerListener);
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		prepareListData();
		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
		// setting list adapter
		expListView.setAdapter(listAdapter);
		View header = getLayoutInflater().inflate(R.layout.nav_header_main, null);
		expListView.addHeaderView(header, null, false);
		fragment = new ScheduleOfClasses();
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
		mDrawerLayout.closeDrawer(expListView);

		expListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				switch (groupPosition) {


				case 0:
					switch (childPosition) {
					case 0:
						mwebView.loadUrl("file:///android_asset/Academics _ Northwestern Polytechnic University.html");
						break;
					case 1:
						mwebView.loadUrl("file:///android_asset/Academic Calendar _ Northwestern Polytechnic University.html");
						break;

					case 2:
						mwebView.loadUrl("file:///android_asset/classSchedule.html");
						break;
						/*case 3:
								setContentView(R.layout.faculty_profile);
								break;*/

					default:
						break;
					}
					break;

				case 1:
					switch (childPosition) {
					case 0:
						mwebView.loadUrl("file:///android_asset/admis_overview.html");
						break;

						case 1:
							mwebView.loadUrl("file:///android_asset/Tuition and Costs.html");;
							break;
						case 2:
							mwebView.loadUrl("file:///android_asset/scholarships.html");;
							break;
					default:
						break;
					}
					break;
				case 2:
					switch (childPosition) {
						case 0:
							mwebView.loadUrl("file:///android_asset/Organizations.html");
							break;
						case 1:
							mwebView.loadUrl("file:///android_asset/Online Bookstore.html");;
							break;
						case 2:
							mwebView.loadUrl("file:///android_asset/Student Services Center.html");;
							break;
						case 3:
							mwebView.loadUrl("file:///android_asset/Housing.html");;

							break;

						default:
							break;
						}
						break;
					case 3:
						switch (childPosition) {
							case 0:


                                LoginButton();


								break;
							case 1:
								LoginButton();
							case 2:
								mwebView.loadUrl("file:///android_asset/logon.html");
								break;


							default:
								break;
						}
						break;
					case 4:
						switch (childPosition) {
							case 0:
								mwebView.loadUrl("file:///android_asset/Contact.html");

								break;
							default:
								break;
						}
						break;

				default:
					break;
				}
				getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
				mDrawerLayout.closeDrawer(expListView);
				return false;
			}
			/*private void facultyprofile(){

				final TextView business;
				final TextView engineering;
				final TextView generaledu;

				business = (TextView)findViewById(R.id.textView2);
				business.setOnClickListener(new View.OnClickListener(){
					public void onClick(View v){
						mwebView.loadUrl("file:///android_asset/Business School Faculty.html");
					}
				});

			}*/

            private void LoginButton() {
                Intent login=new Intent(NavigationActivity.this,HomeActivity.class);
				startActivity(login);

            }
        });
	}

	View.OnClickListener homeOnclickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(mDrawerLayout.isDrawerOpen(expListView)){
				mDrawerLayout.closeDrawer(expListView);
			}else{
				mDrawerLayout.openDrawer(expListView);
			}
		}
	};

	private OnItemClickListener mDrawerItemClickedListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

			switch(position){
			case 0:
				//fragment = new ScheduleOfClasses();
				break;
			case 1:
				//fragment = new Fragment();
				break;
			case 2:
				//fragment = new OnlineClasses();
				break;
			default:
				return;
			}

			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();

			mDrawerLayout.closeDrawer(expListView);
		}
	};

	// Catch the events related to the drawer to arrange views according to this
	// action if necessary...
	private DrawerListener mDrawerListener = new DrawerListener() {

		@Override
		public void onDrawerStateChanged(int status) {

		}

		@Override
		public void onDrawerSlide(View view, float slideArg) {

		}

		@Override
		public void onDrawerOpened(View view) {
		}

		@Override
		public void onDrawerClosed(View view) {
		}
	};

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		//listDataHeader.add("Home");
		listDataHeader.add("Academics");
		listDataHeader.add("Admissions");
		listDataHeader.add("Campus Life");
		//listDataHeader.add("College News");
		listDataHeader.add("MyNPU log-on");


		// Adding child data
		List<String> academics = new ArrayList<String>();
		academics.add("Academics Home");
		academics.add("Academics Calendar");
		academics.add("Schedule of Classes");
		//academics.add("Faculty Profiles");


		//List<String> home = new ArrayList<String>();
		//home.add("");

		List<String> admissions = new ArrayList<String>();
		admissions.add("Overview");
		//admissions.add("Apply Online");

		//admissions.add("Undergraduate Application");
		//admissions.add("Graduate Application");
		admissions.add("Tuition And Costs");
		admissions.add("Scholarships");


		List<String> campuslife = new ArrayList<String>();

		campuslife.add("Student Organizations");
		campuslife.add("Online Bookstore");

		campuslife.add("Student Services Center");

		campuslife.add("Housing");


		/*List<String> news = new ArrayList<String>();
		news.add("NPU News");
		news.add("NPU Input");
		news.add("NPU Spotlight");
		news.add("NPU Globe");
		news.add("NPU Alumni Blogs");
		news.add("MyNPU Connection");*/

		List<String> npulogin = new ArrayList<String>();
		//npulogin.add("Application Log-in");
		//npulogin.add("Faculty Log-in");
		npulogin.add("Student Log-in");
		npulogin.add("Faculty Log-in");
		npulogin.add("OscLogin");
		//npulogin.add("Online Courses Log-in");
		//npulogin.add("NPU Webmail Log-in");


		//listDataChild.put(listDataHeader.get(0), home);
		listDataChild.put(listDataHeader.get(0), academics); // Header, Child data
		listDataChild.put(listDataHeader.get(1), admissions);
		listDataChild.put(listDataHeader.get(2), campuslife);
		//listDataChild.put(listDataHeader.get(4), news);
		listDataChild.put(listDataHeader.get(3), npulogin);


	}
	private class MyWebviewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if (Uri.parse(url).getHost().equals("www.npu.edu")) {
				//open url contents in webview
				return false;
			} else {
				//here open external links in external browser or app
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(intent);
				return true;
			}

		}

		//ProgressDialogue
		ProgressDialog pd = null;

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			pd = new ProgressDialog(NavigationActivity.this);
			pd.setTitle("Please Wait..");
			pd.setMessage("Loading..");
			pd.show();
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			pd.dismiss();
			super.onPageFinished(view, url);
		}
	}

	public class ExpandableListAdapter extends BaseExpandableListAdapter {

		private Context _context;
		private List<String> _listDataHeader; // header titles
		// child data in format of header title, child title
		private HashMap<String, List<String>> _listDataChild;

		public ExpandableListAdapter(Context context, List<String> listDataHeader,
				HashMap<String, List<String>> listChildData) {
			this._context = context;
			this._listDataHeader = listDataHeader;
			this._listDataChild = listChildData;
		}

		@Override
		public Object getChild(int groupPosition, int childPosititon) {
			return this._listDataChild.get(this._listDataHeader.get(groupPosition))
					.get(childPosititon);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, final int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {

			final String childText = (String) getChild(groupPosition, childPosition);

			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.list_item, null);
			}

			TextView txtListChild = (TextView) convertView
					.findViewById(R.id.lblListItem);

			txtListChild.setText(childText);
			return convertView;
		}


		@Override
		public int getChildrenCount(int groupPosition) {
			return this._listDataChild.get(this._listDataHeader.get(groupPosition))
					.size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return this._listDataHeader.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return this._listDataHeader.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			String headerTitle = (String) getGroup(groupPosition);
			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.list_group, null);
			}

			TextView lblListHeader = (TextView) convertView
					.findViewById(R.id.lblListHeader);
			lblListHeader.setTypeface(null, Typeface.BOLD);
			lblListHeader.setText(headerTitle);

			return convertView;
		}


		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}
}
