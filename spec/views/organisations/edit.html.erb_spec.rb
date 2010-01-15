require 'spec_helper'

describe "/organisations/edit.html.erb" do
  include OrganisationsHelper

  before(:each) do
    assigns[:organisation] = @organisation = stub_model(Organisation,
                                                        :new_record? => false,
                                                        :name => "value for name",
                                                        :paypal => "value for paypal",
                                                        :url => "value for url",
                                                        :active => false
    )
  end

  it "renders the edit organisation form" do
    render

    response.should have_tag("form[action=#{organisation_path(@organisation)}][method=post]") do
      with_tag('input#organisation_name[name=?]', "organisation[name]")
      with_tag('input#organisation_paypal[name=?]', "organisation[paypal]")
      with_tag('input#organisation_url[name=?]', "organisation[url]")
      with_tag('input#organisation_active[name=?]', "organisation[active]")
    end
  end
end
