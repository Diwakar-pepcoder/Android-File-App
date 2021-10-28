package downloadhandler;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DownloadFileAdapter extends RecyclerView.Adapter<DownloadFileAdapter.DownloadFileAdapterViewHolder> {

    final private Context context;
    final private ArrayList<DownloadFile> allFiles;

    DownloadFileAdapter(Context context, ArrayList<DownloadFile> allFiles){
        this.context = context;
        this.allFiles = allFiles;
    }

//    @NonNull
    @Override
    public DownloadFileAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadFileAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return allFiles.size();
    }

    public class DownloadFileAdapterViewHolder extends RecyclerView.ViewHolder {
        public DownloadFileAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
