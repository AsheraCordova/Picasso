package com.ashera.picasso;

import com.ashera.image.ITarget;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class PicassoPlugin {
	private static final class TargetImageDownloader implements com.ashera.image.IImageDownloader {
		@Override
		public void download(Object context, String url, Object placeholder, Object error, final ITarget target) {
			Context mycontext = (Context) context;
			Picasso.with(mycontext).load(url).into(new Target() {

				@Override
				public void onBitmapLoaded(Bitmap bitmap, LoadedFrom from) {
					target.onBitmapLoaded(bitmap);
				}

				@Override
				public void onBitmapFailed(final Drawable errorDrawable) {
					target.onBitmapFailed(errorDrawable);
				}

				@Override
				public void onPrepareLoad(final Drawable placeHolderDrawable) {
					target.onPrepareLoad(placeHolderDrawable);
				}
			});

		}
	}

	public static void initPlugin() {
		// start - body
		// end - body
		com.ashera.image.ImageDownloaderFactory.register(new TargetImageDownloader());
	}
}
