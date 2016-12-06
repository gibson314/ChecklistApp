

        package com.cs465.litian.roommate.adapter;

        import android.content.Context;
        import android.graphics.Color;
        import android.graphics.Typeface;
        import android.provider.Settings;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseExpandableListAdapter;
        import android.widget.TextView;

        import com.cs465.litian.roommate.R;
        import com.cs465.litian.roommate.Tools.GlobalParameterApplication;

        import java.util.HashMap;
        import java.util.List;

        public class ExpandableListAdapter extends BaseExpandableListAdapter {

            private Context _context;
            private List<String> _listDataHeader; // header titles
            // child data in format of header title, child title
            private HashMap<String, List<String>> _listDataChild;
            private HashMap<String, Integer> _childtocolor;
            public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                         HashMap<String, List<String>> listChildData) {
                this._context = context;
                this._listDataHeader = listDataHeader;
                this._listDataChild = listChildData;
                this._childtocolor = null;
            }
            public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                         HashMap<String, List<String>> listChildData,
                                         HashMap<String, Integer> ChildToColor) {
                this._context = context;
                this._listDataHeader = listDataHeader;
                this._listDataChild = listChildData;
                this._childtocolor = ChildToColor;
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
                _childtocolor = GlobalParameterApplication._childtocolor;
                if (_childtocolor != null) {
                    Log.i("hello!", _childtocolor.toString());
                    int color = _childtocolor.get(childText);
                    switch (color) {
                        case 0:
                            txtListChild.setBackgroundResource(R.color.white);
                            break;
                        case 1:
                            txtListChild.setBackgroundResource(R.color.avoscloud_blue);
                            break;
                        case 2:
                            txtListChild.setBackgroundResource(R.color.mi);
                            break;
                        case 3:
                            txtListChild.setBackgroundResource(R.color.grey);
                            break;
                        case 4:
                            txtListChild.setBackgroundResource(R.color.tabnotselected);
                            break;
                        default:
                            txtListChild.setBackgroundColor(0xFFFFFFFF);
                            break;
                    }
                }
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