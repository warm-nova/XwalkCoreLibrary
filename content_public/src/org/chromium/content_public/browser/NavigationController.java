// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.chromium.content_public.browser;

import org.chromium.base.VisibleForTesting;

/**
 * The NavigationController Java wrapper to allow communicating with the native
 * NavigationController object.
 */
public interface NavigationController {
    /**
     * @return Whether back navigation is possible from the "current entry".
     */
    boolean canGoBack();

    /**
     * @return Whether forward navigation is possible from the "current entry".
     */
    boolean canGoForward();

    /**
     * @param offset The offset into the navigation history.
     * @return Whether we can move in history by given offset
     */
    boolean canGoToOffset(int offset);

    /**
     * Navigates to the specified offset from the "current entry". Does nothing if the offset is
     * out of bounds.
     * @param offset The offset into the navigation history.
     */
    void goToOffset(int offset);

    /**
     * Navigates to the specified index in the navigation entry for this page.
     * @param index The navigation index to navigate to.
     */
    void goToNavigationIndex(int index);

    /**
     * Goes to the navigation entry before the current one.
     */
    void goBack();

    /**
     * Goes to the navigation entry following the current one.
     */
    void goForward();

    /**
     * Loads the current navigation if there is a pending lazy load (after tab restore).
     */
    public void loadIfNecessary();

    /**
     * Requests the current navigation to be loaded upon the next call to loadIfNecessary().
     */
    public void requestRestoreLoad();

    /**
     * Reload the current page.
     */
    public void reload(boolean checkForRepost);

    /**
     * Reload the current page, ignoring the contents of the cache.
     */
    public void reloadIgnoringCache(boolean checkForRepost);

    /**
     * Cancel the pending reload.
     */
    public void cancelPendingReload();

    /**
     * Continue the pending reload.
     */
    public void continuePendingReload();

    /**
     * Load url without fixing up the url string. Consumers of NavigationController are
     * responsible for ensuring the URL passed in is properly formatted (i.e. the
     * scheme has been added if left off during user input).
     * @param params Parameters for this load.
     */
    public void loadUrl(LoadUrlParams params);

    /**
     * Clears NavigationController's page history in both backwards and
     * forwards directions.
     */
    @VisibleForTesting
    public void clearHistory();

    /**
     * Get a copy of the navigation history of NavigationController.
     * @return navigation history of NavigationController.
     */
    public NavigationHistory getNavigationHistory();

    /**
    * Get the navigation history of NavigationController from current navigation entry index
    * with direction (forward/backward)
    * @param isForward determines forward or backward from current index
    * @param itemLimit maximum number of entries to be retrieved in specified
    * diection.
    * @return navigation history by keeping above constraints.
    */
    public NavigationHistory getDirectedNavigationHistory(boolean isForward, int itemLimit);

    /**
     * Get Original URL for current Navigation entry of NavigationController.
     * @return The original request URL for the current navigation entry, or null if there is no
     *         current entry.
     */
    public String getOriginalUrlForVisibleNavigationEntry();

    /**
     * Clears SSL preferences for this NavigationController.
     */
    public void clearSslPreferences();

    /**
     * Get whether or not we're using a desktop user agent for the currently loaded page.
     * @return true, if use a desktop user agent and false for a mobile one.
     */
    public boolean getUseDesktopUserAgent();

    /**
     * Set whether or not we're using a desktop user agent for the currently loaded page.
     * @param override If true, use a desktop user agent.  Use a mobile one otherwise.
     * @param reloadOnChange Reload the page if the UA has changed.
     */
    public void setUseDesktopUserAgent(boolean override, boolean reloadOnChange);

    /**
     * @return The pending {@link NavigationEntry} for this controller or {@code null} if none
     *         exists.
     */
    public NavigationEntry getPendingEntry();

    /**
     * @return The index of the last committed entry.
     */
    public int getLastCommittedEntryIndex();

    /**
     * Removes the entry at the specified |index|.
     * @return false, if the index is the last committed index or the pending entry. Otherwise this
     *         call discards any transient or pending entries.
     */
    public boolean removeEntryAtIndex(int index);
}
