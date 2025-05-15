import React from 'react'
import ShopDetailComponent from "../../components/shop/ShopDetailComponent";
import HeaderComponent from "../../components/website/HeaderComponent";


const DetailPage = () => {
  return (
    <>
        <div className="page-title" data-aos="fade">
            <HeaderComponent title="Contact" />
            <nav className="breadcrumbs">
                <div className="container">
                    <ol>
                        <li><a href="/">Boutique</a></li>
                        <li className="current">Detail de produit</li>
                    </ol>
                </div>
            </nav>
        </div>
        <ShopDetailComponent/>

    </>
  )
}

export default DetailPage
