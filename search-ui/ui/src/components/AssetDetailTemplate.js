import React from 'react';

const getFacebookCount = props => {
  return getAnalyticCount(props.detail.envelope.instance.analytics.facebook);
};

const getInstagramCount = props => {
  return getAnalyticCount(props.detail.envelope.instance.analytics.instagram);
};

const getTwitterCount = props => {
  return getAnalyticCount(props.detail.envelope.instance.analytics.twitter);
};

const getAnalyticCount = analytics => {
  let count = 0;
  if (analytics != null) {
    for (let key of Object.keys(analytics)) {
      count += analytics[key].value;
    }
  }
  return count;
};

const AssetDetailTemplate = props => {
  return (
    <div class="container">
      <h1>Asset Information</h1>
      <h2>{props.detail.envelope.instance.title}</h2>
      <p>
        <strong>Created</strong> {props.detail.envelope.instance.createdDate} |
        <strong>Modified</strong> {props.detail.envelope.instance.createdDate}
      </p>
      <p>
        <strong>Keywords</strong>
        {props.detail.envelope.instance.keywords.join(', ')}
      </p>
      <h2>Campaign Insights</h2>
      <div class="container">
        <div class="col-md-4">
          <div class="panel panel-info">
            <div class="panel-heading">
              <div class="panel-header">
                <h1 class="panel-title">Facebook</h1>
              </div>
            </div>
            <div class="panel-body text-center text-success">
              <h1>{getFacebookCount(props)}</h1>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="panel panel-info">
            <div class="panel-heading">
              <div class="panel-header">
                <h1 class="panel-title">Instagram</h1>
              </div>
            </div>
            <div class="panel-body text-center text-success">
              <h1>{getInstagramCount(props)}</h1>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="panel panel-info">
            <div class="panel-heading">
              <div class="panel-header">
                <h1 class="panel-title">Twitter</h1>
              </div>
            </div>
            <div class="panel-body text-center text-success">
              <h1>{getTwitterCount(props)}</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AssetDetailTemplate;
