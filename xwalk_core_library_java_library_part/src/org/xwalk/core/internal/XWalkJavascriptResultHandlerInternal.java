// Copyright (c) 2012 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.internal;

import org.chromium.base.ThreadUtils;

@XWalkAPI(impl = XWalkJavascriptResultInternal.class, createInternally = true)
public class XWalkJavascriptResultHandlerInternal implements XWalkJavascriptResultInternal {
    private XWalkContentsClientBridge mBridge;
    private final int mId;

    XWalkJavascriptResultHandlerInternal(XWalkContentsClientBridge bridge, int id) {
        mBridge = bridge;
        mId = id;
    }

    // Never use this constructor.
    // It is only used in XWalkJavascriptResultHandlerBridge.
    XWalkJavascriptResultHandlerInternal() {
        mBridge = null;
        mId = -1;
    }

    @Override
    @XWalkAPI
    public void confirm() {
        confirmWithResult(null);
    }

    @Override
    @XWalkAPI
    public void confirmWithResult(final String promptResult) {
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mBridge != null) {
                    mBridge.confirmJsResult(mId, promptResult);
                }
                mBridge = null;
            }
        });
    }

    @Override
    @XWalkAPI
    public void cancel() {
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mBridge != null) {
                    mBridge.cancelJsResult(mId);
                }
                mBridge = null;
            }
        });
    }
}
