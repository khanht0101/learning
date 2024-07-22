import "core-js/stable";
import React, { Suspense, lazy } from 'react';
import { hydrateRoot, createRoot } from 'react-dom/client';

// const QuestionsPageContainer = lazy(() => import('components/QuestionsPage/QuestionsPageContainer'));
const ResponsiveItemListingPageContainer = lazy(() => import('components/ResponsiveItemListingPage/ResponsiveItemListingPageContainer'));
const StoreLocatorContainer = lazy(() => import('components/StoreLocatorPage/StoreLocatorContainer'));
// const ProductDetailSelectorContainer = lazy(() => import('components/productDetailSelector/ProductDetailSelectorContainer'));
// const ProductNavigationContainer = lazy(() => import('components/productNavigation/productNavigationContainer'));
const SearchPageContainer = lazy(() => import('components/SearchPage/SearchPageContainer'));

const rootElements = document.getElementsByClassName("react-app-root");
for (var i = 0; i < rootElements.length; i++) {
  const rootElement = rootElements[i];
  const page = rootElement.getAttribute('data-page');
  const contentId = rootElement.getAttribute('data-content-id');
  // const productTitle = rootElement.getAttribute('data-product-title');
  const internationalText = rootElement.getAttribute('data-international-dealers-text');
  const internationalLink = rootElement.getAttribute('data-international-dealers-link');
  const ssrRendered = rootElement.hasChildNodes();

  const App = () => {
    switch (page) {
      // case 'QuestionsPage':       
      //   return (<Suspense fallback={<div></div>}>
      //     <QuestionsPageContainer categoryId={contentId} />
      //   </Suspense>);
      case 'ResponsiveItemListingPage':
        const itemsPerPage = parseInt(rootElement.getAttribute('data-item-per-row'), 10);
        const filterBy = JSON.parse(rootElement.getAttribute('data-filtering-by'));
        const isGroupBySubCategory = rootElement.getAttribute('data-isgroupby-subcategory') === 'True';
        const itemView = rootElement.getAttribute('data-template-name');
        const enabledFilter = rootElement.getAttribute('data-enabled-filtering') === 'True';
        const primaryFilterIds = JSON.parse(rootElement.getAttribute('data-primary-filter-ids'));
        const includedContentIds = JSON.parse(rootElement.getAttribute('data-included-items'));
        const sortBy = rootElement.getAttribute('data-sort-by');
        const orderType = rootElement.getAttribute('data-order-type');
        const contentType = rootElement.getAttribute('data-content-type');
        const defaultFilteringBy = JSON.parse(rootElement.getAttribute('data-default-filter'));
        const groupByFilter = rootElement.getAttribute('data-group-by-filter');
        const viewMoreLabel = rootElement.getAttribute('data-view-more-label');
        const viewDetailsLabel = rootElement.getAttribute('data-view-details-label');
        const pagingItems = JSON.parse(rootElement.getAttribute('data-paging-items'));
        return <ResponsiveItemListingPageContainer
          categoryId={contentId}
          itemsPerPage={itemsPerPage}
          isGroupBySubCategory={isGroupBySubCategory}
          itemView={itemView}
          enabledFilter={enabledFilter}
          filterBy={filterBy}
          includedContentIds={includedContentIds}
          sortBy={sortBy}
          orderType={orderType}
          contentType={contentType}
          primaryFilterIds={primaryFilterIds}
          defaultFilteringBy={defaultFilteringBy}
          viewMoreLabel={viewMoreLabel}
          viewDetailsLabel={viewDetailsLabel}
          groupByFilter={groupByFilter}
          pagingItems={pagingItems}
          ssrRendered={ssrRendered} />;
      case 'StoreLocatorPage':
        return (<Suspense fallback={<div></div>}><StoreLocatorContainer contentId={contentId} internationalText={internationalText} internationalLink={internationalLink} /></Suspense>);
      // case 'ProductDetailPage':
      //   return (<Suspense fallback={<div></div>}><ProductDetailSelectorContainer contentId={contentId} productTitle={productTitle} /></Suspense>);
      // case 'ProductNavigationPage':
      //   return (<Suspense fallback={<div></div>}><ProductNavigationContainer contentId={contentId} /></Suspense>);
      case 'SearchPage':
      default:
        const productsPath = rootElement.getAttribute('data-products-path');
        const blogsPath = rootElement.getAttribute('data-blogs-path');
        const otherNodePaths = JSON.parse(rootElement.getAttribute('data-others-paths'));
        return (<Suspense fallback={<div></div>}><SearchPageContainer productsPath={productsPath} blogsPath={blogsPath} otherNodePaths={otherNodePaths} /></Suspense>);
    }
  };

  if (ssrRendered) {
        setTimeout(function () {
          hydrateRoot(rootElement, <App />);
        }, 100);
    } else {
        const root = createRoot(rootElement);
        root.render(<App />);
    }
}