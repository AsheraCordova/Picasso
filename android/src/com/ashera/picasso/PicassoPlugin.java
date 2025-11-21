//start - license
/*
 * Copyright (c) 2025 Ashera Cordova
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
//end - license
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
