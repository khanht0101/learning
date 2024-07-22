import "core-js/stable";
import React, { Suspense, lazy } from 'react';
import { createRoot } from 'react-dom/client';
const ResourceListingPageContainer = lazy(() => import('components/resourceListingPage/ResourceListingPageContainer'));
const SearchResourcePageContainer = lazy(() => import('components/resourceListingPage/SearchResourcePageContainer'));

const rootElements = document.getElementsByClassName("react-app-root");

for (var i = 0; i < rootElements.length; i++) {
  const rootElement = rootElements[i];
  const page = rootElement.getAttribute('data-page');

  const App = () => {
    switch (page) {
      case 'ResourceListingPage':
        const contentId = rootElement.getAttribute('data-content-id');
        return (<Suspense fallback={<div></div>}>
          <ResourceListingPageContainer contentId={contentId} />
            </Suspense>);
        case 'SearchResourcePage':
        const rootId = rootElement.getAttribute('data-root-id');
        return (<Suspense fallback={<div></div>}>
            <SearchResourcePageContainer rootId={rootId} />
        </Suspense>);
      default:
        return null;
    }
  };

  const root = createRoot(rootElement);
  root.render(<App />);
}