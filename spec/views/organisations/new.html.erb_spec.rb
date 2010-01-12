require 'spec_helper'

describe "/organisations/new.html.erb" do
  include OrganisationsHelper

  before(:each) do
    assigns[:organisation] = stub_model(Organisation,
      :new_record? => true,
      :name => "value for name",
      :paypal => "value for paypal",
      :url => "value for url",
      :active => false
    )
  end

  it "renders new organisation form" do
    render

    response.should have_tag("form[action=?][method=post]", organisations_path) do
      with_tag("input#organisation_name[name=?]", "organisation[name]")
      with_tag("input#organisation_paypal[name=?]", "organisation[paypal]")
      with_tag("input#organisation_url[name=?]", "organisation[url]")
      with_tag("input#organisation_active[name=?]", "organisation[active]")
    end
  end
end
