import React from 'react';
import { CardResult } from 'grove-core-react-components';

const CustomSearchResultHeader = props => {
  return (
    <div class="panel-header">
      <h1 class="panel-title">
        {props.result.extracted.content[0].instance.title}
      </h1>
    </div>
  );
};

const CustomSearchResultContent = props => {
  return (
    <div>
      <p>
        <strong>Created</strong>{' '}
        {props.result.extracted.content[0].instance.createdDate}
      </p>
      <p>
        <strong>Modified</strong>{' '}
        {props.result.extracted.content[0].instance.createdDate}{' '}
      </p>
      <p>
        <strong>Keywords</strong>{' '}
        {props.result.extracted.content[0].instance.keywords.join(', ')}
      </p>
    </div>
  );
};

const AssetSearchResult = props => {
  // 1. Suppress the header, though you could change the header instead.
  // 2. Add 'You got a result!' to each result content using the above component.
  return (
    <CardResult
      result={props.result}
      detailPath={props.detailPath}
      header={CustomSearchResultHeader}
      content={CustomSearchResultContent}
    />
  );
};

export default AssetSearchResult;
