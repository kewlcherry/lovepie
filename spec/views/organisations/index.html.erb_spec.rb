require 'spec_helper'

describe "/organisations/index.html.erb" do
  include OrganisationsHelper

  before(:each) do
    assigns[:organisations] = [
      stub_model(Organisation,
        :name => "value for name",
        :paypal => "value for paypal",
        :url => "value for url",
        :active => false
      ),
      stub_model(Organisation,
        :name => "value for name",
        :paypal => "value for paypal",
        :url => "value for url",
        :active => false
      )
    ]
  end

  it "renders a list of organisations" do
    render
    response.should have_tag("tr>td", "value for name".to_s, 2)
    response.should have_tag("tr>td", "value for paypal".to_s, 2)
    response.should have_tag("tr>td", "value for url".to_s, 2)
    response.should have_tag("tr>td", false.to_s, 2)
  end
end
