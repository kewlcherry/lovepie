require 'spec_helper'

describe "/organisations/show.html.erb" do
  include OrganisationsHelper
  before(:each) do
    assigns[:organisation] = @organisation = stub_model(Organisation,
      :name => "value for name",
      :paypal => "value for paypal",
      :url => "value for url",
      :active => false
    )
  end

  it "renders attributes in <p>" do
    render
    response.should have_text(/value\ for\ name/)
    response.should have_text(/value\ for\ paypal/)
    response.should have_text(/value\ for\ url/)
    response.should have_text(/false/)
  end
end
